package pe.com.hiper.bmatic.management.perfilagendamiento.infrastructure.schedule;

import org.springframework.stereotype.Component;
import pe.com.hiper.bmatic.management.perfilagendamiento.domain.schedule.model.Schedule;
import pe.com.hiper.bmatic.management.perfilagendamiento.domain.schedule.repository.ScheduleRepository;

import java.util.List;

@Component
public class ScheduleRepositoryImpl implements ScheduleRepository {

    private final ScheduleJdbcClient jdbcClient;

    public ScheduleRepositoryImpl(ScheduleJdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    @Override
    public void saveSchedules(List<Schedule> scheduleList, Integer schedulingId) {
        deleteSchedulesById(schedulingId, true);
        jdbcClient.saveSchedules(scheduleList);
    }

    @Override
    public void deleteSchedulesById(Integer schedulingId, boolean filter) {
        this.jdbcClient.deleteSchedulesById(schedulingId, filter);
    }

    @Override
    public List<Schedule> getListSchedulesById(Integer schedulingId) {
        return this.jdbcClient.getSchedulesById(schedulingId);
    }
}
