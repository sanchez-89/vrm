package com.vuelogix.recordingmanager.recording.model;

import com.vuelogix.recordingmanager.framework.entity.AbstractEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "vrm_recording")

public class Recording extends AbstractEntity {


    //    @NotBlank
    @Column(name = "recording_id")
    String recordingId;


    @NotBlank
    @Column(name = "url")
    String url;

    //    @NotBlank
    @Column(name = "recording_duration")
    int recordingDuration;

    //    @NotBlank
    @Column(name = "mpeg_url")
    String mpegUrl;

    //    @NotBlank
    @Column(name = "total_users")
    int totalUsers;

    //    @NotBlank
    @Column(name = "record_flag")
    boolean record_flag;

    //    @NotBlank
    @Column(name = "retry_count")
    int retryCount;

    //    @NotBlank
    @Column(name = "recording_status")
    String recordingStatus;

    //    @NotBlank
    @Column(name = "screen_number")
    int screenNumber;

    //    @NotBlank
    @Column(name = "file_split_duration")
    int fileSplitDuration;

    //    @NotBlank
    @Column(name = "recording_parent_path")
    String recordingParentPath;

    //    @NotBlank
    @Column(name = "recording_file_directory_path")
    String recordingFileDirectoryPath;

    public String getRecordingId() {
        return recordingId;
    }

    public void setRecordingId(String recordingId) {
        this.recordingId = recordingId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getRecordingDuration() {
        return recordingDuration;
    }

    public void setRecordingDuration(int recordingDuration) {
        this.recordingDuration = recordingDuration;
    }

    public String getMpegUrl() {
        return mpegUrl;
    }

    public void setMpegUrl(String mpegUrl) {
        this.mpegUrl = mpegUrl;
    }

    public int getTotalUsers() {
        return totalUsers;
    }

    public void setTotalUsers(int totalUsers) {
        this.totalUsers = totalUsers;
    }

    public boolean isRecord_flag() {
        return record_flag;
    }

    public void setRecord_flag(boolean record_flag) {
        this.record_flag = record_flag;
    }

    public int getRetryCount() {
        return retryCount;
    }

    public void setRetryCount(int retryCount) {
        this.retryCount = retryCount;
    }

    public String getRecordingStatus() {
        return recordingStatus;
    }

    public void setRecordingStatus(String recordingStatus) {
        this.recordingStatus = recordingStatus;
    }

    public int getScreenNumber() {
        return screenNumber;
    }

    public void setScreenNumber(int screenNumber) {
        this.screenNumber = screenNumber;
    }

    public int getFileSplitDuration() {
        return fileSplitDuration;
    }

    public void setFileSplitDuration(int fileSplitDuration) {
        this.fileSplitDuration = fileSplitDuration;
    }

    public String getRecordingParentPath() {
        return recordingParentPath;
    }

    public void setRecordingParentPath(String recordingParentPath) {
        this.recordingParentPath = recordingParentPath;
    }

    public String getRecordingFileDirectoryPath() {
        return recordingFileDirectoryPath;
    }

    public void setRecordingFileDirectoryPath(String recordingFileDirectoryPath) {
        this.recordingFileDirectoryPath = recordingFileDirectoryPath;
    }
}
