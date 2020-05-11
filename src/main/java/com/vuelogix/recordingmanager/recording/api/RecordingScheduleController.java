package com.vuelogix.recordingmanager.recording.api;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.vuelogix.recordingmanager.framework.utils.Constants;
import com.vuelogix.recordingmanager.recording.api.response.SaveScheduleData;
import com.vuelogix.recordingmanager.recording.api.response.Scheduler;
import com.vuelogix.recordingmanager.recording.api.response.VrStatus;
import com.vuelogix.recordingmanager.recording.model.RecordingSchedule;
import com.vuelogix.recordingmanager.recording.service.RecordingScheduleDefenitionService;
import com.vuelogix.recordingmanager.recording.service.ScheduledRecordingService;

/**
 * @author Arun AP <arun.ap@vuelogix.com>
 * @since 10-Sep-2019
 *
 */
@RestController
@RequestMapping("/api/v1/scheduler")
public class RecordingScheduleController {

	private static final Logger LOG = LoggerFactory.getLogger(RecordingScheduleController.class);
	@Autowired
	private RecordingScheduleDefenitionService recordingScheduleDefenitionService;

	@Autowired
	private ScheduledRecordingService scheduledRecordingService;

	@PostMapping(value = "/save")
	public @ResponseBody SaveScheduleData saveSchedules(@RequestBody List<Scheduler> listSchedulers) {
		LOG.info("From saving schedules, listSchedules :: " + listSchedulers);
		SimpleDateFormat sdf = new SimpleDateFormat(Constants.STANDARD_TIME_PATTERN);
		List<Scheduler> saveScheduleResponses = new ArrayList<Scheduler>();
		SaveScheduleData saveScheduleData = new SaveScheduleData();
		VrStatus status = new VrStatus();
		status.setType("SUCCESS");
		for (Scheduler scheduler : listSchedulers) {
			RecordingSchedule recordingScheduler = new RecordingSchedule();
			Scheduler saveScheduleResponse = scheduler;
			recordingScheduler.setRtspLink(scheduler.getRtspLink());
			recordingScheduler.setFormat(scheduler.getDateFormat());
			boolean save = true;
			try {
				recordingScheduler.setStartTime(new Time(sdf.parse(scheduler.getStartTime()).getTime()));
				recordingScheduler.setEndTime(new Time(sdf.parse(scheduler.getEndTime()).getTime()));
			} catch (ParseException e) {
				save = false;
				LOG.warn("Error while parsing string to time " + e.getMessage());
			}
			if (save) {
				recordingScheduler = recordingScheduleDefenitionService.saveRecordingSchedule(recordingScheduler);
			}
			saveScheduleResponse.setScheduleId(recordingScheduler.getId().intValue());
			saveScheduleResponses.add(saveScheduleResponse);
		}
		saveScheduleData.setData(saveScheduleResponses);
		saveScheduleData.setStatus(status);
		return saveScheduleData;
	}

	@PostMapping(value = "/getScheduledRecords")
	public String getScheduledRecordingDetails(@RequestBody List<Scheduler> listSchedulers) {
		LOG.info("Inside getScheduledRecordingDetails");
		if (listSchedulers != null) {
			return scheduledRecordingService.getScheduleInstanceVideos(listSchedulers);
		} else {
			LOG.info("Received request is null or empty :: " + listSchedulers);
			return null;
		}
	}
	
	@PostMapping(value = "/delete")
	public @ResponseBody VrStatus deleteSingleSchedule(@RequestBody Long vrmRecordingSchedulerId) {
		if (LOG.isDebugEnabled()) {
			LOG.debug("vrmRecordingSchedulerId :: " + vrmRecordingSchedulerId);
		}
		return recordingScheduleDefenitionService.updateSingleSchedule(vrmRecordingSchedulerId);
	}
}
