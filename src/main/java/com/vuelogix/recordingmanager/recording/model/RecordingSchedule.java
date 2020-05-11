package com.vuelogix.recordingmanager.recording.model;

import java.sql.Time;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.vuelogix.recordingmanager.framework.entity.AbstractEntity;

/**
 * @author Arun AP <arun.ap@vuelogix.com>
 * @since 10-Sep-2019
 *
 */
@Entity
@Table(name = "vrm_recording_scheduler")

public class RecordingSchedule extends AbstractEntity {

	@NotNull
	@Column(name = "rtsp_link")
	String rtspLink;

	@NotNull
	@Column(name = "start_time")
	Time startTime;

	@NotNull
	@Column(name = "end_time")
	Time endTime;

	@NotBlank
	@Column(name = "date_format")
	String dateFormat;
	
	@NotNull
	@Column(name = "is_disabled")
	Integer isDisabled = 0;

	public String getRtspLink() {
		return rtspLink;
	}

	public void setRtspLink(String rtspLink) {
		this.rtspLink = rtspLink;
	}

	public Time getStartTime() {
		return startTime;
	}

	public void setStartTime(Time startTime) {
		this.startTime = startTime;
	}

	public Time getEndTime() {
		return endTime;
	}

	public void setEndTime(Time endTime) {
		this.endTime = endTime;
	}

	public String getDateFormat() {
		return dateFormat;
	}

	public void setDateFormat(String dateFormat) {
		this.dateFormat = dateFormat;
	}

	public String getFormat() {
		return dateFormat;
	}

	public void setFormat(String dateFormat) {
		this.dateFormat = dateFormat;
	}

	public Integer getIsDisabled() {
		return isDisabled;
	}

	public void setIsDisabled(Integer isDisabled) {
		this.isDisabled = isDisabled;
	}
}
