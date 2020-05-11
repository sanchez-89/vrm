package com.vuelogix.recordingmanager.recording.api.request;


import com.vuelogix.recordingmanager.framework.rest.AbstractRequest;

import java.util.List;

public class VSHeartBeatRequest extends AbstractRequest {


    List<HeartBeatStatus> statuses;

    public List<HeartBeatStatus> getStatuses() {
        return statuses;
    }

    public void setStatuses(List<HeartBeatStatus> statuses) {
        this.statuses = statuses;
    }

    @Override
    public String toString() {
        return "VSHeartBeatRequest{" +
            "statuses=" + statuses +
            "} " + super.toString();
    }
}
