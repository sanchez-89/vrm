package com.vuelogix.recordingmanager.recording.repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.vuelogix.recordingmanager.recording.model.RecordingScheduleInstance;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * @author Arun AP <arun.ap@vuelogix.com>
 * @since 13-Sep-2019
 */
@Repository
public interface ScheduledRecordingRepository extends CrudRepository<RecordingScheduleInstance, Integer> {

    @Query(nativeQuery = true, value = "SELECT * FROM vrm_scheduler_recorder_mapping t where t.schedule_id = ?1")
    public ArrayList<RecordingScheduleInstance> findScheduledRecords(int scheduleId);

    @Query(value = "SELECT vrm FROM RecordingScheduleInstance vrm WHERE DATE(vrm.date) IN :dateRange")
    public ArrayList<RecordingScheduleInstance> scheduledRecordsByDates(@Param("dateRange") List<Date> dateRange);

    @Query(value = "SELECT vrm FROM RecordingScheduleInstance vrm WHERE  vrm.date between :startDate and :endDate AND scheduleId IN :scheduleIds")
    public ArrayList<RecordingScheduleInstance> scheduledRecordsBySheduleIdDate(@Param("scheduleIds") List<Long> scheduleIds,
                                                                                @Param("startDate") Date startDate, @Param("endDate") Date endDate);

    @Query(nativeQuery = true, value = "SELECT * FROM vrm_scheduler_recorder_mapping t where t.status IN :statuses")
    public ArrayList<RecordingScheduleInstance> findScheduledStatus(@Param("statuses") List<String> statuses);

    @Query(nativeQuery = true, value = "SELECT * FROM vrm_scheduler_recorder_mapping t WHERE DATE(t.date) = :date and t.schedule_id = :scheduleId")
    public RecordingScheduleInstance scheduleInstanceByScheduleIdAndDate(@Param("date") String date, @Param("scheduleId") Long scheduleId);

    @Query(value = "SELECT max(date) FROM RecordingScheduleInstance")
    public Date maxScheduleInstanceDate();

    @Query(value = "FROM RecordingScheduleInstance WHERE scheduleId=:scheduleId AND recordingId=:recordingId")
    public RecordingScheduleInstance findByscheduleIdAndrecordingId(long scheduleId, String recordingId);
}
