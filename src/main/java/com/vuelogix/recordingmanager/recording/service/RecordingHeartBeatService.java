package com.vuelogix.recordingmanager.recording.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vuelogix.recordingmanager.framework.utils.DateUtility;
import com.vuelogix.recordingmanager.framework.utils.RestUtils;
import com.vuelogix.recordingmanager.recording.api.request.HeartBeatStatus;
import com.vuelogix.recordingmanager.recording.api.request.VRHeartBeatRequest;
import com.vuelogix.recordingmanager.recording.api.request.VSHeartBeatRequest;
import com.vuelogix.recordingmanager.recording.api.response.VRHeartBeatResponse;
import com.vuelogix.recordingmanager.recording.api.response.VSHeartBeatResponse;
import com.vuelogix.recordingmanager.recording.model.RecordingScheduleInstance;
import com.vuelogix.recordingmanager.recording.repository.ScheduledRecordingRepository;

@Service
@Transactional // will move to a configuration later
public class RecordingHeartBeatService {

    private static final Logger LOG = LoggerFactory.getLogger(RecordingHeartBeatService.class);

    @Autowired
    ScheduledRecordingService scheduledRecordingService;

    @Autowired
    ScheduledRecordingRepository recordingRepository;

    @Value("${vr.base.url}${vr.heartbeat.path}")
    String vrHeartbeatUrl;

    @Value("${vs.base.url}${vs.heartbeat.path}")
    String vsHeartbeatUrl;

    @Value("${vs.heartbeat.interval.hour}")
    int vrHeartbeatInterval;

    public void fetchAndSendHeartBeat() {
        if (LOG.isDebugEnabled()) {
            LOG.debug("heart beat url VR " + vrHeartbeatUrl);
            LOG.debug("heart beat url VS " + vsHeartbeatUrl);
            LOG.debug("heart beat interval " + vrHeartbeatInterval);
        }
        VRHeartBeatResponse response = RestUtils.post(vrHeartbeatUrl, new VRHeartBeatRequest(vrHeartbeatInterval),
            VRHeartBeatResponse.class);
        LOG.debug("Response from VR {}", response);

        VSHeartBeatRequest vsHeartBeatRequest = new VSHeartBeatRequest();
        vsHeartBeatRequest.setStatuses(response.getStatuses());
        VSHeartBeatResponse vsHeartBeatResponse = RestUtils.post(vsHeartbeatUrl, vsHeartBeatRequest,
            VSHeartBeatResponse.class);
        if (LOG.isDebugEnabled()) {
            LOG.debug("Response from VS {}", vsHeartBeatResponse);
        }
        updateScheduledRecordingStatus(response);
    }

    private void updateScheduledRecordingStatus(VRHeartBeatResponse response) {
        ArrayList<Date> dateRange = new ArrayList<>();
        dateRange.add(new Date());
        dateRange.add(DateUtility.yesterday());
        ArrayList<RecordingScheduleInstance> listScheduledRecords = scheduledRecordingService
            .getScheduledRecordsByDates(dateRange);
        Map<String, RecordingScheduleInstance> scheduledRecordMap = new HashMap<String, RecordingScheduleInstance>();
        List<String> recordingIds = new ArrayList<String>();
        for (RecordingScheduleInstance recordingScheduleInstance : listScheduledRecords) {
            recordingIds.add(recordingScheduleInstance.getRecordingId());
            scheduledRecordMap.put(recordingScheduleInstance.getRecordingId(), recordingScheduleInstance);
        }
        for (HeartBeatStatus heartBeatStatus : response.getStatuses()) {
            // TODO use map instead of list in
            if (recordingIds.contains(heartBeatStatus.getRecordingId())) {
                RecordingScheduleInstance recordingScheduleInstance = scheduledRecordMap
                    .get(heartBeatStatus.getRecordingId());
                recordingScheduleInstance.setStatus(heartBeatStatus.getCurrentStatus());
                recordingRepository.save(recordingScheduleInstance);
            }
        }
    }
}
