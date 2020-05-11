package com.vuelogix.recordingmanager.recording.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vuelogix.recordingmanager.framework.utils.Constants;
import com.vuelogix.recordingmanager.framework.utils.DateUtility;
import com.vuelogix.recordingmanager.framework.utils.RestUtils;
import com.vuelogix.recordingmanager.recording.api.request.StartRecordingRequest;
import com.vuelogix.recordingmanager.recording.api.response.CameraRecordFetchData;
import com.vuelogix.recordingmanager.recording.api.response.Scheduler;
import com.vuelogix.recordingmanager.recording.api.response.StartRecordingResponse;
import com.vuelogix.recordingmanager.recording.api.response.VideoFetchData;
import com.vuelogix.recordingmanager.recording.api.response.VideoFetchDetails;
import com.vuelogix.recordingmanager.recording.api.response.VrStatus;
import com.vuelogix.recordingmanager.recording.model.RecordingSchedule;
import com.vuelogix.recordingmanager.recording.model.RecordingScheduleInstance;
import com.vuelogix.recordingmanager.recording.repository.ScheduledRecordingRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Arun AP <arun.ap@vuelogix.com>
 * @since 13-Sep-2019
 */

@Service
@Transactional
public class ScheduledRecordingService {

    private static final Logger LOG = LoggerFactory.getLogger(ScheduledRecordingService.class);

    @Autowired
    ScheduledRecordingRepository scheduledRecordingRepository;

    @Autowired
    RecordingScheduleDefenitionService recordingScheduleDefenitionService;

    @Value("${vr.base.url}")
    private String recorderUrl;

    @Value("${vr.startrecording.path}")
    private String recorderStartRecordingUrl;

    @Value("${endDateTimeAdd.sec}")
    private int endDateTimeAddSeconds;

    @Value("${vr.base.url}${vr.video.path}")
    String vrRecordedVideoUrl;

    /**
     * @return ArrayList<RecordingScheduleInstance>
     * @author Arun AP <arun.ap@vuelogix.com>
     * @since 18-Sep-2019
     */
    public ArrayList<RecordingScheduleInstance> getScheduledRecordsByDates(ArrayList<Date> dates) {
        return scheduledRecordingRepository.scheduledRecordsByDates(dates);
    }

    /**
     * @return ArrayList<RecordingScheduleInstance>
     * @author Arun AP <arun.ap@vuelogix.com>
     * @since 19-Sep-2019
     */
    public ArrayList<RecordingScheduleInstance> scheduledRecordsBySheduleIdDate(List<Long> scheduleIds, Date startDate,
                                                                                Date endDate) {
        if (LOG.isDebugEnabled()) {
            LOG.debug(" Reach scheduledRecordsBySheduleIdDate ");
        }
        ArrayList<RecordingScheduleInstance> savedScheduledRecords = scheduledRecordingRepository
            .scheduledRecordsBySheduleIdDate(scheduleIds, startDate, endDate);
        return savedScheduledRecords;
    }

    /**
     * @return String
     * @author Arun AP <arun.ap@vuelogix.com>
     * @since 19-Sep-2019
     */
    public VideoFetchData getScheduledRecordingDetails(ArrayList<RecordingScheduleInstance> scheduledRecords,
                                                       Date startDate, Date endDate) {
        if (LOG.isDebugEnabled()) {
            LOG.debug("Reach getScheduledRecordingDetails");
        }
        List<String> recordingIdList = new ArrayList<String>();
        Map<String, String> scheduleStartAndEndMap = new HashMap<String, String>();
        Map<String, Date> recordingIdDateMap = new HashMap<String, Date>();
        Map<String, Long> recordingIdScheduleIdMap = new HashMap<String, Long>();
        /* it will create startTime and endTime map with recordingId */
        createStartTimeEndTimeMapwithRecId(scheduleStartAndEndMap, recordingIdList, scheduledRecords,
            recordingIdDateMap, recordingIdScheduleIdMap);
        String startTime = DateUtility.formatDate(startDate, Constants.DATE_FORMAT_JSON_STANDARD);
        String endTime = DateUtility.formatDate(endDate, Constants.DATE_FORMAT_JSON_STANDARD);
        VideoFetchData videoFetchData = RestUtils.post(vrRecordedVideoUrl,
            new CameraRecordFetchData(recordingIdList, startTime, endTime, true), VideoFetchData.class);
        VideoFetchData videoFetchDataOutput = new VideoFetchData();
        if (videoFetchData != null) {
            for (VideoFetchDetails videoFetchDetails : videoFetchData.getRecordingIdList()) {
                videoFetchDetails.setVideoDate(recordingIdDateMap.get(videoFetchDetails.getRecordingId()));
                videoFetchDetails.setScheduleId(recordingIdScheduleIdMap.get(videoFetchDetails.getRecordingId()).intValue());
            }
            videoFetchDataOutput = addMissedRecordingIds(videoFetchData, recordingIdList, scheduleStartAndEndMap,
                recordingIdDateMap, recordingIdScheduleIdMap);
        } else {
            LOG.warn("Error received from VR while fetching recorded data,skipping and continuing");
        }
        return videoFetchDataOutput;
    }

    /**
     * @return RecordingScheduleInstance
     * @author Arun AP <arun.ap@vuelogix.com>
     * @since 01-Oct-2019
     */
    public boolean isScheduleInstanceExistsForDate(Date date, Long scheduleId) {
        RecordingScheduleInstance savedRecordingScheduleInstance = scheduledRecordingRepository
            .scheduleInstanceByScheduleIdAndDate(DateUtility.formatDate(date, "yyyy-MM-dd"), scheduleId);
        if (LOG.isDebugEnabled()) {
            LOG.debug("result is " + savedRecordingScheduleInstance);
        }
        return savedRecordingScheduleInstance != null;
    }

    public void startScheduledRecording() {
        List<RecordingSchedule> recordingSchedulersList = recordingScheduleDefenitionService.getActiveSchedules();
        List<RecordingScheduleInstance> listScheduledRecordInstance = new ArrayList<RecordingScheduleInstance>();

        /* get last scheduleInstance date */
        Date lastScheduleInstanceDate = scheduledRecordingRepository.maxScheduleInstanceDate();
        if (lastScheduleInstanceDate == null) {
            lastScheduleInstanceDate = new Date();
        }

        /* Creating list of scheduleInstance dates( to execute schedules) */
        List<Date> scheduleInstanceDates = DateUtility.getDatesBetweenDates(lastScheduleInstanceDate, new Date());
        for (Date dateInstance : scheduleInstanceDates) {
            Calendar currDateCalendar = Calendar.getInstance();
            for (RecordingSchedule recordingSchedule : recordingSchedulersList) {
                Date scheduleStartTime = calculateStartDateTime(dateInstance, currDateCalendar, recordingSchedule.getStartTime());
                if (!isScheduleInstanceExistsForDate(scheduleStartTime, recordingSchedule.getId())) {
                    record(listScheduledRecordInstance, dateInstance, currDateCalendar, recordingSchedule);
                    if (listScheduledRecordInstance.size() >= 10)
                        break;
                }
            }
        }
    }

    // run in a new transaction
    private void record(List<RecordingScheduleInstance> listScheduledRecordInstance, Date currentDate,
                        Calendar currDateCalendar, RecordingSchedule recordingSchedule) {

        Date scheduledStartTime = calculateStartDateTime(currentDate, currDateCalendar,
            recordingSchedule.getStartTime());
        /*
         * using calculateStartDateTime bcz need to calculate file split duration
         * without adding more 5 seconds
         */
        Date scheduledEndTime = calculateStartDateTime(currentDate, currDateCalendar, recordingSchedule.getEndTime());

        int seconds = findDurationInSeconds(scheduledStartTime, scheduledEndTime);
        if (LOG.isDebugEnabled()) {
            LOG.debug("file split duration :: " + seconds);
        }
        scheduledEndTime = calculateEndDateTime(currentDate, currDateCalendar, recordingSchedule.getEndTime());

        String rtspUrl = buildRtspUrl(recordingSchedule, scheduledStartTime, scheduledEndTime);
        StartRecordingResponse response = RestUtils.post(recorderUrl + recorderStartRecordingUrl,
            new StartRecordingRequest(rtspUrl, seconds, true), StartRecordingResponse.class);
        if (response.isError()) {
            LOG.warn("Error received from VR while starting recording ,skipping and continuing"
                + response.getErrorMessage());
        } else {
            if (DateUtility.nullCheck(response.getRecordingId())) {
                RecordingScheduleInstance recordingScheduleInstance = new RecordingScheduleInstance();
                recordingScheduleInstance.setDate(scheduledStartTime);
                recordingScheduleInstance.setScheduleId(recordingSchedule.getId());
                recordingScheduleInstance.setRecordingId(response.getRecordingId());
                recordingScheduleInstance.setStatus(Constants.SCHEDULED_RECORD_JUST_REQUESTED);
                scheduledRecordingRepository.save(recordingScheduleInstance);
                if (LOG.isDebugEnabled()) {
                    LOG.debug("Requested and log updated to :: Just Requested");
                }
                listScheduledRecordInstance.add(recordingScheduleInstance);
            } else {
                LOG.warn("RecordingId is null response from VR - " + response);
            }
        }
    }

    private String buildRtspUrl(RecordingSchedule recordingSchedule, Date scheduledStartTime, Date scheduledEndTime) {
        String startDateTime = DateUtility.formatDate(scheduledStartTime, recordingSchedule.getDateFormat());
        String endDateTime = DateUtility.formatDate(scheduledEndTime, recordingSchedule.getDateFormat());

        String rtspUrl = recordingSchedule.getRtspLink()
            .replace(Constants.STREAMING_SYNTAX_START_DATE_TIME, startDateTime)
            .replace(Constants.STREAMING_SYNTAX_END_DATE_TIME, endDateTime);
        if (LOG.isDebugEnabled()) {
            LOG.debug("rtsp url of schedule id " + recordingSchedule.getId() + " is:: " + rtspUrl);
        }
        return rtspUrl;
    }

    private int findDurationInSeconds(Date scheduledStartTime, Date scheduledEndTime) {
        /* total file duration in seconds */
        long diff = scheduledEndTime.getTime() - scheduledStartTime.getTime();
        long diffSeconds = (diff / (60 * 1000) * 60) + (diff / 1000 % 60);
        return (int) Math.abs(diffSeconds);
    }

    private Date calculateEndDateTime(Date currentDateTimeInstance, Calendar currDateCalendar, Time time) {
        currDateCalendar.setTime(DateUtility.concatDateAndTimeToDate(currentDateTimeInstance, time, null));
        currDateCalendar.add(Calendar.SECOND, endDateTimeAddSeconds);
        if (currDateCalendar.getTime().after(new Date())) {
            currDateCalendar.add(Calendar.DATE, -1);
        }
        return currDateCalendar.getTime();
    }

    private Date calculateStartDateTime(Date currentDateTimeInstance, Calendar currDateCalendar, Time time) {
        currDateCalendar.setTime(DateUtility.concatDateAndTimeToDate(currentDateTimeInstance, time, null));
        if (currDateCalendar.getTime().after(new Date())) {
            currDateCalendar.add(Calendar.DATE, -1);
        }
        return currDateCalendar.getTime();
    }

    private void createStartTimeEndTimeMapwithRecId(Map<String, String> scheduleStartAndEndMap,
                                                    List<String> recordingIdList, ArrayList<RecordingScheduleInstance> scheduledRecords,
                                                    Map<String, Date> recordingIdDateMap, Map<String, Long> recordingIdScheduleIdMap) {
        if (LOG.isDebugEnabled()) {
            LOG.debug("Reach createStartTimeEndTimeMapwithRecId");
        }
        for (RecordingScheduleInstance records : scheduledRecords) {
            recordingIdList.add(records.getRecordingId());
            RecordingSchedule vrmRecordingScheduler = recordingScheduleDefenitionService
                .findById(records.getScheduleId());
            scheduleStartAndEndMap.put(records.getRecordingId(),
                vrmRecordingScheduler.getStartTime() + "," + vrmRecordingScheduler.getEndTime());
            RecordingScheduleInstance recordingScheduleInstance = scheduledRecordingRepository.
                findByscheduleIdAndrecordingId(vrmRecordingScheduler.getId(), records.getRecordingId());
            recordingIdDateMap.put(records.getRecordingId(), recordingScheduleInstance.getDate());
            recordingIdScheduleIdMap.put(records.getRecordingId(), recordingScheduleInstance.getScheduleId());
        }
    }

    /**
     * @return String
     * @author Arun AP <arun.ap@vuelogix.com>
     * @since 14-Oct-2019
     */
    public String getScheduleInstanceVideos(List<Scheduler> listSchedulers) {
        if (LOG.isDebugEnabled()) {
            LOG.debug("Reach getScheduleInstanceVideos");
        }
        VideoFetchData scheduledRecordsResponse = null;
        ArrayList<RecordingScheduleInstance> scheduledRecords = new ArrayList<RecordingScheduleInstance>();
        Date startDate = DateUtility.parseDate(listSchedulers.get(0).getStartTime(),
            Constants.DATE_FORMAT_JQUERY_CALENDAR);
        Date endDate = DateUtility.parseDate(listSchedulers.get(0).getEndTime(), Constants.DATE_FORMAT_JQUERY_CALENDAR);
        List<Long> schedulerIds = new ArrayList<Long>();
        for (Scheduler scheduler : listSchedulers) {
            schedulerIds.add(Long.parseLong(scheduler.getScheduleId() + ""));
        }
        scheduledRecords = scheduledRecordsBySheduleIdDate(schedulerIds, startDate, endDate);
        if (scheduledRecords.size() > 0) {
            scheduledRecordsResponse = getScheduledRecordingDetails(scheduledRecords, startDate, endDate);
        } else {
            if (LOG.isDebugEnabled()) {
                LOG.debug("No Scheduled Recordings Available in vrm for schedule ids :: " + schedulerIds);
            }
            VrStatus vrStatus = new VrStatus("No schedules available for this date", "FAILED");
            List<VideoFetchDetails> recordingIdList = null;
            scheduledRecordsResponse = new VideoFetchData(recordingIdList, vrStatus);
        }
        ObjectMapper Obj = new ObjectMapper();
        String response = "";
        try {
            response = Obj.writeValueAsString(scheduledRecordsResponse);
        } catch (JsonProcessingException e) {
            LOG.warn("JsonProcessingException :: " + e.getMessage());
        }
        return response;
    }

    /**
     * @return VideoFetchData
     * @author Arun AP <arun.ap@vuelogix.com>
     * @since 14-Oct-2019
     */
    private VideoFetchData addMissedRecordingIds(VideoFetchData videoFetchData, List<String> recordingIdList,
                                                 Map<String, String> scheduleStartAndEndMap, Map<String, Date> recordingIdDateMap,
                                                 Map<String, Long> recordingIdScheduleIdMap) {
        if (LOG.isDebugEnabled()) {
            LOG.debug("Reach addMissedRecordingIds");
        }
        List<VideoFetchDetails> videoFetchDetails = new ArrayList<VideoFetchDetails>();
        List<VideoFetchDetails> videoFetchDetailsOutput = new ArrayList<VideoFetchDetails>();
        VideoFetchData videoFetchDataOutput = videoFetchData;
        videoFetchDetails = videoFetchData.getRecordingIdList();
        if (videoFetchDetails.size() > 0) {
            Boolean isThere = false;
            for (String recordingId : recordingIdList) {
                isThere = false;
                for (VideoFetchDetails video : videoFetchDetails) {
                    if (recordingId.equals(video.getRecordingId())) {
                        String[] timeStartAndEnd = scheduleStartAndEndMap.get(video.getRecordingId()).split(",");
                        video.setStartTime(timeStartAndEnd[0]);
                        video.setEndTime(timeStartAndEnd[1]);
                        isThere = true;
                    }
                }
                if (isThere == false) {
                    String[] timeStartAndEnd = scheduleStartAndEndMap.get(recordingId).split(",");
                    VideoFetchDetails newVideo = new VideoFetchDetails(timeStartAndEnd[1], recordingId, "",
                        timeStartAndEnd[0], "", "", "");
                    newVideo.setVideoDate(recordingIdDateMap.get(recordingId));
                    newVideo.setScheduleId(recordingIdScheduleIdMap.get(recordingId).intValue());
                    videoFetchDataOutput.getRecordingIdList().add(newVideo);
                }
            }
        } else {
            VrStatus vrStatus = new VrStatus("", "OK");
            videoFetchDataOutput.setStatus(vrStatus);
            for (String recId : recordingIdList) {
                String[] endTimeAndStartTime = scheduleStartAndEndMap.get(recId).split(",");
                VideoFetchDetails newVideo = new VideoFetchDetails(endTimeAndStartTime[1], recId, "",
                    endTimeAndStartTime[0], "", "", "");
                newVideo.setVideoDate(recordingIdDateMap.get(recId));
                newVideo.setScheduleId(recordingIdScheduleIdMap.get(recId).intValue());
                videoFetchDetailsOutput.add(newVideo);
            }
            videoFetchDataOutput.setRecordingIdList(videoFetchDetailsOutput);
        }
        return videoFetchDataOutput;
    }
}
