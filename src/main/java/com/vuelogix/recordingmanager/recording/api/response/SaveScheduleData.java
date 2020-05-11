package com.vuelogix.recordingmanager.recording.api.response;

import java.util.List;

public class SaveScheduleData {
	private List<Scheduler> data;
	private VrStatus status;

	public List<Scheduler> getData() {
		return data;
	}

	public void setData(List<Scheduler> data) {
		this.data = data;
	}

	public VrStatus getStatus() {
		return status;
	}

	public void setStatus(VrStatus status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "SaveScheduleData [data=" + data + ", status=" + status + "]";
	}
}
