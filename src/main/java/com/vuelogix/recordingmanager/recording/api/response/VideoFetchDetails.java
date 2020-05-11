package com.vuelogix.recordingmanager.recording.api.response;

import java.util.Date;

/**
 * @author Arun AP <arun.ap@vuelogix.com>
 * @since 27-Sep-2019
 */
public class VideoFetchDetails {
    private String endTime;
    private String recordingId;
    private String size;
    private String startTime;
    private String status;
    private String thumbnailLink;
    private String videoLink;
    private Date videoDate;
    private int scheduleId;

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getRecordingId() {
        return recordingId;
    }

    public void setRecordingId(String recordingId) {
        this.recordingId = recordingId;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getThumbnailLink() {
        return thumbnailLink;
    }

    public void setThumbnailLink(String thumbnailLink) {
        this.thumbnailLink = thumbnailLink;
    }

    public String getVideoLink() {
        return videoLink;
    }

    public void setVideoLink(String videoLink) {
        this.videoLink = videoLink;
    }

    public Date getVideoDate() {
        return videoDate;
    }

    public void setVideoDate(Date videoDate) {
        this.videoDate = videoDate;
    }

    public int getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(int scheduleId) {
        this.scheduleId = scheduleId;
    }

    public VideoFetchDetails() {
        super();
        // TODO Auto-generated constructor stub
    }

    public VideoFetchDetails(String endTime, String recordingId, String size, String startTime, String status,
                             String thumbnailLink, String videoLink) {
        super();
        this.endTime = endTime;
        this.recordingId = recordingId;
        this.size = size;
        this.startTime = startTime;
        this.status = status;
        this.thumbnailLink = thumbnailLink;
        this.videoLink = videoLink;
    }

    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

}
