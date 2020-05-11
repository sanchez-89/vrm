package com.vuelogix.recordingmanager.recording.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.vuelogix.recordingmanager.framework.entity.AbstractEntity;

/**
 * @author Arun AP <arun.ap@vuelogix.com>
 * @since 10-Sep-2019
 *
 */

@Entity
@Table(name = "vrm_scheduler_recorder_mapping")
public class RecordingScheduleInstance extends AbstractEntity {

	@NotNull
	@Column(name = "schedule_id")
	Long scheduleId;

	@Column(name = "recording_id")
	String recordingId;

	@NotNull
	@Column(name = "date")
	Date date;

	@NotNull
	@Column(name = "status")
	String status;

	public String getRecordingId() {
		return recordingId;
	}

	public Long getScheduleId() {
		return scheduleId;
	}

	public void setScheduleId(Long scheduleId) {
		this.scheduleId = scheduleId;
	}

	public void setRecordingId(String recordingId) {
		this.recordingId = recordingId;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getStatus() {
		return status;
	}

	@Override
	public String toString() {
		return "RecordingScheduleInstance [scheduleId=" + scheduleId + ", recordingId=" + recordingId + ", date=" + date
				+ ", status=" + status + "]";
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
