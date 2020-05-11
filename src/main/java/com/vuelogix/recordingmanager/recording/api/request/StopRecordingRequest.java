package com.vuelogix.recordingmanager.recording.api.request;

import com.vuelogix.recordingmanager.framework.rest.AbstractRequest;

public class StopRecordingRequest extends AbstractRequest {

    private String rtspUrl;

    public String getRtspUrl() {
        return rtspUrl;
    }

    public void setRtspUrl(String rtspUrl) {
        this.rtspUrl = rtspUrl;
    }
}
