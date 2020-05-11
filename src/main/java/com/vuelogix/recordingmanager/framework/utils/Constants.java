package com.vuelogix.recordingmanager.framework.utils;

/**
 * @author Arun AP <arun.ap@vuelogix.com>
 * @since 17-Sep-2019
 *
 */
public class Constants {
	/* related to scheduled record status */
	public static final String SCHEDULED_RECORD_JUST_REQUESTED = "Just Requested";
	public static final String SCHEDULED_RECORD_COMPLETED = "Completed";
	public static final String SCHEDULED_RECORD_ERROR = "Error";
	public static final String SCHEDULED_RECORD_ONGOING = "Ongoing";
	
	/* related to rtsp url */
	public static final String STREAMING_SYNTAX_START_DATE_TIME = "<START_DATE_TIME>";
	public static final String STREAMING_SYNTAX_END_DATE_TIME = "<END_DATE_TIME>";
	
	/* related to VR */
	public static final String STREAMER_BASE_URL = "http://localhost:8070/vr";
	public static final String STREAMER_RECORDED_VIDEO_URL = "/api/v1/recording/videos";
	
	public static final String DATE_FORMAT_SQL = "yyyy-MM-dd HH:mm:ss";
	public static final String DATE_FORMAT_JSON_STANDARD = "yyyy-MM-dd'T'HH:mm:ssZ";
	public static final String STANDARD_TIME_PATTERN = "HH:mm:ss";
	public static final String DATE_FORMAT_JQUERY_CALENDAR = "EEE MMM dd yyyy HH:mm:ss ";
}
