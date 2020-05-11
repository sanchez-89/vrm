package com.vuelogix.recordingmanager.recording.scheduling;

import com.vuelogix.recordingmanager.recording.service.RecordingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


@Component
public class RestartFailedRecordingScheduler {
    private static final Logger LOG = LoggerFactory.getLogger(RestartFailedRecordingScheduler.class);

    @Autowired
    RecordingService service;

    @Scheduled(cron = "${restartFailedRecording.cron}")
    public void fixedRate() {
    	LOG.debug("inside scheduler");
        service.restartFailedRecordings();
        LOG.debug("complete scheduler");
    }
}
