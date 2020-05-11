package com.vuelogix.recordingmanager.recording.api.response;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * @author Arun AP <arun.ap@vuelogix.com>
 * @since 27-Sep-2019
 *
 */
public class VrStatus {
	private String errorMessage;
	private String type;

	public String getErrorMessage() {
		return errorMessage;
	}

	public VrStatus(String errorMessage, String type) {
		super();
		this.errorMessage = errorMessage;
		this.type = type;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getType() {
		return type;
	}

	public VrStatus() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void setType(String type) {
		this.type = type;
	}
}
