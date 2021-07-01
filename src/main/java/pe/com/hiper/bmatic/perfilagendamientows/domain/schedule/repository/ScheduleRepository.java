package pe.com.hiper.bmatic.perfilagendamientows.domain.schedule.repository;

import pe.com.hiper.bmatic.perfilagendamientows.domain.schedule.model.Schedule;

import java.util.List;

public interface ScheduleRepository {

    void saveSchedules(List<Schedule> scheduleList, Integer schedulingId);

    void deleteSchedulesById(Integer schedulingId, boolean filter);

    List<Schedule> getListSchedulesById(Integer schedulingId);
}
