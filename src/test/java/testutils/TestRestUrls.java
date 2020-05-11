package testutils;

import com.vuelogix.recordingmanager.framework.utils.RestUtils;
import com.vuelogix.recordingmanager.recording.api.request.StartRecordingRequest;
import com.vuelogix.recordingmanager.recording.api.response.StartRecordingResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

public class TestRestUrls {
    private static final Map<String, Object> emptyMap = new HashMap<>();
    Logger LOG = LogManager.getLogger(TestRestUrls.class);

    public static void main(String[] args) {

        for (int i = 0; i < 1; i++) {
            new Thread(() -> {
                TestRestUrls testRestUrls = new TestRestUrls();
                testRestUrls.callHello();
//                testRestUrls.loadStartRecording();
            }).start();
        }
    }


    private void callHello() {
        String url = "http://localhost:8070/api/v1/recording/hello";
        LOG.debug(RestUtils.get(url, emptyMap, String.class));
    }

    private void loadStartRecording() {
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            String url = "http://localhost:8080/api/v1/recording/startRecordingForEver";
            StartRecordingRequest recordingRequest = new StartRecordingRequest("temp",1,false);
            recordingRequest.setRtspURL("some url");
            LOG.debug(RestUtils.post(url, recordingRequest, StartRecordingResponse.class));
        }
    }
}
