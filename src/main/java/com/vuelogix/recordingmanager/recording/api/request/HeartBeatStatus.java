package com.vuelogix.recordingmanager.recording.api.request;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class HeartBeatStatus {
    String recordingId;
    String currentStatus;
    Date lastUpdatedTime;
    int fileSize;
    int retryCount;
    Date recordStartTime;
    Date recordEndTime;

    public Date getLastUpdatedTime() {
        return lastUpdatedTime;
    }

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss.SSSSSS")
    public void setLastUpdatedTime(Date lastUpdatedTime) {
        this.lastUpdatedTime = lastUpdatedTime;
    }

    public String getRecordingId() {
        return recordingId;
    }

    public void setRecordingId(String recordingId) {
        this.recordingId = recordingId;
    }

    public String getCurrentStatus() {
        return currentStatus;
    }

    public void setCurrentStatus(String currentStatus) {
        this.currentStatus = currentStatus;
    }

    public int getFileSize() {
        return fileSize;
    }

    public void setFileSize(int fileSize) {
        this.fileSize = fileSize;
    }

    public int getRetryCount() {
        return retryCount;
    }

    public void setRetryCount(int retryCount) {
        this.retryCount = retryCount;
    }

    public Date getRecordStartTime() {
        return recordStartTime;
    }

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss.SSSSSS")
    public void setRecordStartTime(Date recordStartTime) {
        this.recordStartTime = recordStartTime;
    }

    public Date getRecordEndTime() {
        return recordEndTime;
    }

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss.SSSSSS")
    public void setRecordEndTime(Date recordEndTime) {
        this.recordEndTime = recordEndTime;
    }

    @Override
    public String toString() {
        return "HeartBeatStatus{" +
            "recordingId='" + recordingId + '\'' +
            ", currentStatus='" + currentStatus + '\'' +
            ", lastUpdatedTime=" + lastUpdatedTime +
            ", fileSize=" + fileSize +
            ", retryCount=" + retryCount +
            ", recordStartTime=" + recordStartTime +
            ", recordEndTime=" + recordEndTime +
            '}';
    }
}
