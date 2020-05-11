package com.vuelogix.recordingmanager.recording.api.request;

import com.vuelogix.recordingmanager.framework.rest.AbstractRequest;

public class StartRecordingRequest extends AbstractRequest {

    private String rtspURL;
    private int fileSplitDuration;
    private Boolean scheduled = true;

    

	public String getRtspURL() {
		return rtspURL;
	}

	public void setRtspURL(String rtspURL) {
		this.rtspURL = rtspURL;
	}

	public int getFileSplitDuration() {
		return fileSplitDuration;
	}

	public void setFileSplitDuration(int fileSplitDuration) {
		this.fileSplitDuration = fileSplitDuration;
	}

	public Boolean getScheduled() {
		return scheduled;
	}

	public void setScheduled(Boolean scheduled) {
		this.scheduled = scheduled;
	}

	public StartRecordingRequest(String rtspURL, int fileSplitDuration, Boolean scheduled) {
		super();
		this.rtspURL = rtspURL;
		this.fileSplitDuration = fileSplitDuration;
		this.scheduled = scheduled;
	}


	 
    
    
}
