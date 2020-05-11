package com.vuelogix.recordingmanager.recording.scheduling;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.vuelogix.recordingmanager.recording.service.RecordingHeartBeatService;

@Component

public class RecordingHealthCheckScheduler {
    private static final Logger LOG = LoggerFactory.getLogger(RecordingHealthCheckScheduler.class);

    @Autowired
    RecordingHeartBeatService service;

    public RecordingHealthCheckScheduler() {
        LOG.info("Starting ....");

    }

    @Scheduled(cron = "${hearbeat.cron}")
    public void fixedRate() {
    	LOG.debug("inside scheduler");
        service.fetchAndSendHeartBeat();
        LOG.debug("complete scheduler");
    }

}
