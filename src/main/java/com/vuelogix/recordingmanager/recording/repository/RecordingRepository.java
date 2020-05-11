package com.vuelogix.recordingmanager.recording.repository;

import com.vuelogix.recordingmanager.recording.model.Recording;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecordingRepository extends CrudRepository<Recording, Long> {
}
