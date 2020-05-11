package com.vuelogix.recordingmanager.recording.service;

import java.util.ArrayList;
import java.util.List;

import com.vuelogix.recordingmanager.recording.api.response.VrStatus;
import com.vuelogix.recordingmanager.recording.model.RecordingSchedule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vuelogix.recordingmanager.recording.repository.RecordingScheduleRepository;

/**
 * @author Arun AP <arun.ap@vuelogix.com>
 * @since 10-Sep-2019
 */
@Service
@Transactional
public class RecordingScheduleDefenitionService {

    private static final Logger LOG = LoggerFactory.getLogger(RecordingScheduleDefenitionService.class);

    @Autowired
    RecordingScheduleRepository recordingScheduleRepository;

    public RecordingSchedule saveRecordingSchedule(RecordingSchedule recordingScheduler) {
        return recordingScheduleRepository.save(recordingScheduler);
    }

    /**
     * @return Status
     * @author Arun Ap
     * @since 18-Mar-2020
     */
    public VrStatus updateSingleSchedule(long vrmRecordingSchedulerId) {
        if (LOG.isDebugEnabled()) {
            LOG.debug("vrmRecordingSchedulerId - " + vrmRecordingSchedulerId);
        }
        VrStatus responseStatus = new VrStatus();
        String type = "FAILED";
        String errorMessage = "";
        if (vrmRecordingSchedulerId > 0) {
            RecordingSchedule recordingSchedule = findById(vrmRecordingSchedulerId);
            if (recordingSchedule != null) {
                recordingSchedule.setIsDisabled(1);
                RecordingSchedule updatedRecordingSchedule = recordingScheduleRepository.save(recordingSchedule);
                if (updatedRecordingSchedule.getIsDisabled() == 1) {
                    type = "OK";
                    errorMessage = "Successfully Deleted";
                }
            } else {
                errorMessage = "Failed to delete";
                if (LOG.isDebugEnabled()) {
                    LOG.debug("recordingSchedule is null - " + errorMessage);
                }
            }
        } else {
            errorMessage = "Failed to delete";
            if (LOG.isDebugEnabled()) {
                LOG.debug("vrmRecordingSchedulerId is not valid - " + errorMessage);
            }
        }
        responseStatus.setType(type);
        responseStatus.setErrorMessage(errorMessage);
        return responseStatus;
    }

    public ArrayList<RecordingSchedule> getAllRecordingSchedules() {
        return (ArrayList<RecordingSchedule>) recordingScheduleRepository
            .findAll();
    }

    public RecordingSchedule findById(Long id) {
        return recordingScheduleRepository.findById(id).get();
    }

    public List<RecordingSchedule> getActiveSchedules() {
        return (List<RecordingSchedule>) recordingScheduleRepository.getActiveSchedules();
    }
}
