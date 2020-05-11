package com.vuelogix.recordingmanager.recording.api.response;

import com.vuelogix.recordingmanager.framework.rest.AbstractResponse;
import com.vuelogix.recordingmanager.recording.api.request.HeartBeatStatus;

import java.util.List;


public class VRHeartBeatResponse extends AbstractResponse {

    List<HeartBeatStatus> statuses;

    public List<HeartBeatStatus> getStatuses() {
        return statuses;
    }

    public void setStatuses(List<HeartBeatStatus> statuses) {
        this.statuses = statuses;
    }

    @Override
    public String toString() {
        return "VRHeartBeatResponse{" +
            "statuses=" + statuses +
            "} " + super.toString();
    }
}

