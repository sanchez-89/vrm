package com.vuelogix.recordingmanager.framework.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Map;

public class RestUtils {

    private static final Logger LOG = LoggerFactory.getLogger(RestUtils.class);
    private static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm a z");

    public static <T> T get(String url, Map<String, ?> params, Class<T> responseType) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(url, responseType, params);
    }

    public static <T> T post(String url, Object request, Class<T> responseType) {
        LOG.debug("Calling url {}", url);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.setDateFormat(DATE_FORMAT);
            String jsonRequest = objectMapper.writeValueAsString(request);
            HttpEntity<String> entity = new HttpEntity<>(jsonRequest, headers);

            RestTemplate restTemplate = new RestTemplate();
            return restTemplate.postForObject(url, entity, responseType);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
