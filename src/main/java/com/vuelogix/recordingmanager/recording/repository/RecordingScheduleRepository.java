package com.vuelogix.recordingmanager.recording.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.vuelogix.recordingmanager.recording.model.RecordingSchedule;

/**
 * @author Arun AP <arun.ap@vuelogix.com>
 * @since 10-Sep-2019
 *
 */
@Repository
public interface RecordingScheduleRepository extends CrudRepository<RecordingSchedule, Long>{
	
	@Query(value = "FROM RecordingSchedule WHERE isDisabled=0")
	public List<RecordingSchedule> getActiveSchedules();
}
