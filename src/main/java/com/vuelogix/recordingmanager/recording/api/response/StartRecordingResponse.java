package com.vuelogix.recordingmanager.recording.api.response;

import com.vuelogix.recordingmanager.framework.rest.AbstractResponse;

public class StartRecordingResponse extends AbstractResponse {

    private String recordingId;

    public String getRecordingId() {
        return recordingId;
    }

    public void setRecordingId(String recordingId) {
        this.recordingId = recordingId;
    }

    @Override
    public String toString() {
        return "StartRecordingResponse{" +
            "recordingId='" + recordingId + '\'' +
            '}';
    }
}
