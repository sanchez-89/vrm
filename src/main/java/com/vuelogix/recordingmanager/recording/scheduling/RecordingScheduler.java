package com.vuelogix.recordingmanager.recording.scheduling;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.vuelogix.recordingmanager.recording.service.ScheduledRecordingService;

@Component
public class RecordingScheduler {
	private static final Logger LOG = LoggerFactory.getLogger(RecordingScheduler.class);

	@Autowired
	ScheduledRecordingService scheduledRecordingService;

	@Scheduled(cron = "${recordingScheduler.cron}")
	public void recordingScheduler() {
		LOG.debug("inside scheduler");
		scheduledRecordingService.startScheduledRecording();
		LOG.debug("complete scheduler");
	}
}
