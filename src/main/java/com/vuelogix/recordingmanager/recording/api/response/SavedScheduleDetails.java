package com.vuelogix.recordingmanager.recording.api.response;

public class SavedScheduleDetails {
	private String vsScheduleId;
	private String vrmScheduleId;

	public String getVsScheduleId() {
		return vsScheduleId;
	}
	public void setVsScheduleId(String vsScheduleId) {
		this.vsScheduleId = vsScheduleId;
	}
	public String getVrmScheduleId() {
		return vrmScheduleId;
	}
	public void setVrmScheduleId(String vrmScheduleId) {
		this.vrmScheduleId = vrmScheduleId;
	}
}
