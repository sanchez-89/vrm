package com.vuelogix.recordingmanager.recording.api.response;

import java.util.List;

public class CameraRecordFetchData {
	private List<String> recordingIdList;
	private String startTime;
	private String endTime;
	private Boolean isScheduled;

	public List<String> getRecordingIdList() {
		return recordingIdList;
	}
	public void setRecordingIdList(List<String> recordingIdList) {
		this.recordingIdList = recordingIdList;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public CameraRecordFetchData(List<String> recordingIdList, String startTime, String endTime, Boolean isScheduled) {
		super();
		this.recordingIdList = recordingIdList;
		this.startTime = startTime;
		this.endTime = endTime;
		this.isScheduled = isScheduled;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public Boolean getIsScheduled() {
		return isScheduled;
	}
	public void setIsScheduled(Boolean isScheduled) {
		this.isScheduled = isScheduled;
	}


}
