package com.vuelogix.recordingmanager.recording.service;

import com.vuelogix.recordingmanager.framework.utils.RestUtils;
import com.vuelogix.recordingmanager.recording.api.request.StartRecordingRequest;
import com.vuelogix.recordingmanager.recording.api.response.StartRecordingResponse;
import com.vuelogix.recordingmanager.recording.model.Recording;
import com.vuelogix.recordingmanager.recording.repository.RecordingRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional //will move to a configuration later
public class RecordingService {

    private static final Logger LOG = LoggerFactory.getLogger(RecordingService.class);

    @Autowired
    RecordingRepository recordingRepository;

    @Value("${vr.base.url}/${vr.startrecording.path}")
    private String startRecordingUrl;

    @Value("${vr.base.url}/${vr.restartFailedRecording.path}")
    private String restartFailedRecordingUrl;

    public StartRecordingResponse startRecordingForEver(String rtspUrl) {
        LOG.debug("Rtsp url {}", rtspUrl);
        Recording recording = new Recording();
        recording.setUrl(rtspUrl);
        StartRecordingRequest request = new StartRecordingRequest(rtspUrl, 12, false);
        StartRecordingResponse response = RestUtils.post(startRecordingUrl, request, StartRecordingResponse.class);
        LOG.debug("Saved {}", response);
        recording.setRecordingId(response.getRecordingId());
        recordingRepository.save(recording);
        return response;
    }

    //TODO will accept the schedule also
    public StartRecordingResponse startScheduledRecording(String rtspUrl) {
        throw new RuntimeException("Not implemented");
    }

    public String restartFailedRecordings() {
        String response = RestUtils.post(restartFailedRecordingUrl, "", String.class);
        LOG.debug(response);
        return response;
    }
}
