package com.vuelogix.recordingmanager.recording.api;

import com.vuelogix.recordingmanager.framework.utils.RestUtils;
import com.vuelogix.recordingmanager.recording.api.request.StartRecordingRequest;
import com.vuelogix.recordingmanager.recording.api.response.StartRecordingResponse;
import com.vuelogix.recordingmanager.recording.service.RecordingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping("/api/v1/recording")
public class RecordingController {

    @Autowired
    private RecordingService recordingService;

    @Value("${vr.base.url}")
    private String recorderUrl;

    @Value("${vr.startrecording.path}")
    private String recorderStartRecordingUrl;

    @GetMapping(value = "/hello")
    public String sayHello() {
        return "Hello";
    }

    @PostMapping(value = "/startRecordingForEver")
    public StartRecordingResponse startRecordingForEver(@RequestBody StartRecordingRequest request) {
        StartRecordingResponse response = recordingService.startRecordingForEver(request.getRtspURL());
        return response;
    }

    //TODO This method will be removed in future a more and more features are added in this application.
    // Till then just act as a proxy.
    @RequestMapping(value = "/*", method = POST, consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    @ResponseBody
    public String passThrough(HttpServletRequest httpServletRequest, HttpEntity<String> httpEntity) {
        String json = httpEntity.getBody();
        String requestUrl = httpServletRequest.getRequestURI();
        String response = RestUtils.post(recorderUrl + requestUrl, json, String.class);

        return response;
    }

}
