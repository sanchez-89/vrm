package com.vuelogix.recordingmanager.recording.api.response;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Scheduler {
	private int cameraId;
	private int cameraTreeId;
	private int scheduleId = 0;
	private int id;
	private int httpPort;
	private int rtspPort;
	private int vsRecordingSchedulerId;
	private String scheduleName;
	private String rtspLink;
	private String ip;
	private String channel;
	private String username;
	private String password;
	private String startTime;
	private String endTime;
	private String dateFormat;
	private String type;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MMM d, yyyy h:mm:ss a")
	private Date createdOn;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MMM d, yyyy h:mm:ss a")
	private Date updatedOn;
	private boolean isDisabled;

    /* getters and setters */
    public int getCameraId() {
        return cameraId;
    }

    public void setCameraId(int cameraId) {
        this.cameraId = cameraId;
    }

    public int getCameraTreeId() {
        return cameraTreeId;
    }

    public int getHttpPort() {
        return httpPort;
    }

    public void setHttpPort(int httpPort) {
        this.httpPort = httpPort;
    }

    public int getRtspPort() {
        return rtspPort;
    }

    public void setRtspPort(int rtspPort) {
        this.rtspPort = rtspPort;
    }

    public int getVsRecordingSchedulerId() {
        return vsRecordingSchedulerId;
    }

    public void setVsRecordingSchedulerId(int vsRecordingSchedulerId) {
        this.vsRecordingSchedulerId = vsRecordingSchedulerId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public void setCameraTreeId(int cameraTreeId) {
        this.cameraTreeId = cameraTreeId;
    }

    public int getScheduleId() {
        return scheduleId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getDateFormat() {
        return dateFormat;
    }

    public void setDateFormat(String dateFormat) {
        this.dateFormat = dateFormat;
    }

    public void setScheduleId(int scheduleId) {
        this.scheduleId = scheduleId;
    }

    public String getScheduleName() {
        return scheduleName;
    }

    public void setScheduleName(String scheduleName) {
        this.scheduleName = scheduleName;
    }

    public String getRtspLink() {
        return rtspLink;
    }

    public void setRtspLink(String rtspLink) {
        this.rtspLink = rtspLink;
    }

    public Date getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(Date updatedOn) {
        this.updatedOn = updatedOn;
    }

	public boolean isDisabled() {
		return isDisabled;
	}

	public void setDisabled(boolean isDisabled) {
		this.isDisabled = isDisabled;
	}

	@Override
    public String toString() {
        return "Scheduler [cameraId=" + cameraId + ", cameraTreeId=" + cameraTreeId + ", scheduleId=" + scheduleId
            + ", id=" + id + ", httpPort=" + httpPort + ", rtspPort=" + rtspPort + ", vsRecordingSchedulerId="
            + vsRecordingSchedulerId + ", scheduleName=" + scheduleName + ", rtspLink=" + rtspLink + ", ip=" + ip
            + ", channel=" + channel + ", username=" + username + ", password=" + password + ", startTime="
            + startTime + ", endTime=" + endTime + ", dateFormat=" + dateFormat + ", type=" + type + ", createdOn="
            + createdOn + ", updatedOn=" + updatedOn + ", isDisabled=" + isDisabled + "]";
    }

    public Scheduler(int cameraId, int cameraTreeId, int scheduleId, int id, int httpPort, int rtspPort,
                     int vsRecordingSchedulerId, String scheduleName, String rtspLink, String ip, String channel,
                     String username, String password, String startTime, String endTime, String dateFormat, String type,
                     Date createdOn, Date updatedOn, Boolean isDisabled) {
        super();
        this.cameraId = cameraId;
        this.cameraTreeId = cameraTreeId;
        this.scheduleId = scheduleId;
        this.id = id;
        this.httpPort = httpPort;
        this.rtspPort = rtspPort;
        this.vsRecordingSchedulerId = vsRecordingSchedulerId;
        this.scheduleName = scheduleName;
        this.rtspLink = rtspLink;
        this.ip = ip;
        this.channel = channel;
        this.username = username;
        this.password = password;
        this.startTime = startTime;
        this.endTime = endTime;
        this.dateFormat = dateFormat;
        this.type = type;
        this.createdOn = createdOn;
        this.updatedOn = updatedOn;
        this.isDisabled = isDisabled;
    }

    public Scheduler() {
        super();
    }

}
