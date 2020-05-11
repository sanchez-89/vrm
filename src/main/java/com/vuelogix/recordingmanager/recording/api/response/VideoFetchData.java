package com.vuelogix.recordingmanager.recording.api.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * @author Arun AP <arun.ap@vuelogix.com>
 * @since 27-Sep-2019
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class VideoFetchData {

	private List<VideoFetchDetails> recordingIdList;

	private VrStatus status;


	public VrStatus getStatus() {
		return status;
	}

	public void setStatus(VrStatus status) {
		this.status = status;
	}


	public List<VideoFetchDetails> getRecordingIdList() {
		return recordingIdList;
	}

	public void setRecordingIdList(List<VideoFetchDetails> recordingIdList) {
		this.recordingIdList = recordingIdList;
	}

	public VideoFetchData(List<VideoFetchDetails> recordingIdList, VrStatus status) {
		super();
		this.recordingIdList = recordingIdList;
		this.status = status;
	}

	public VideoFetchData() {
		super();
		// TODO Auto-generated constructor stub
	}

}
