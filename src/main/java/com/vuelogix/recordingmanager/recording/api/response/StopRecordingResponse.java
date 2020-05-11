package com.vuelogix.recordingmanager.recording.api.response;

import com.vuelogix.recordingmanager.framework.rest.AbstractResponse;

public class StopRecordingResponse extends AbstractResponse {

    private String rtspUrl;

    public String getRtspUrl() {
        return rtspUrl;
    }

    public void setRtspUrl(String rtspUrl) {
        this.rtspUrl = rtspUrl;
    }
}
