package com.vuelogix.recordingmanager.recording.api.request;


import com.vuelogix.recordingmanager.framework.rest.AbstractRequest;

import java.util.Calendar;
import java.util.Date;

public class VRHeartBeatRequest extends AbstractRequest {

    Date lastUpdatedTime;

    public VRHeartBeatRequest(int vrHeartbeatInterval) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.HOUR, vrHeartbeatInterval * -1);
        Date today = calendar.getTime();
        lastUpdatedTime = today;
    }

    public VRHeartBeatRequest() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        Date today = calendar.getTime();
        lastUpdatedTime = today;
    }

    public Date getLastUpdatedTime() {
        return lastUpdatedTime;
    }
}
