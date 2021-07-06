package pe.com.hiper.bmatic.management.perfilagendamiento.application.shedule;

import org.springframework.stereotype.Component;
import pe.com.hiper.bmatic.management.perfilagendamiento.domain.schedule.model.Schedule;
import pe.com.hiper.bmatic.management.perfilagendamiento.domain.schedule.repository.ScheduleRepository;

import java.util.ArrayList;
import java.util.List;

@Component
public class ScheduleServiceImpl implements ScheduleService {

    private final ScheduleRepository scheduleRepository;

    public ScheduleServiceImpl(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    @Override
    public void saveSchedules(List<CreateScheduleCommand> commandList, Integer schedulingId) {
        List<Schedule> scheduleList = new ArrayList<>();
        if (!commandList.isEmpty()) {
            commandList.forEach(o -> {
                scheduleList.add(Schedule.builder().bookingType(o.getBookingType()).day(o.getDay())
                        .schedulingId(o.getSchedulingId()).addDating(o.getAddDating()).date(o.getDate())
                        .counterId(o.getCounterId()).counterTypeId(o.getCounterTypeId()).start(o.getStartHour())
                        .end(o.getEndHour()).build());
            });
        }
        scheduleRepository.saveSchedules(scheduleList, schedulingId);

    }

    @Override
    public List<Schedule> getListSchedules(Integer schedulingId) {
        return scheduleRepository.getListSchedulesById(schedulingId);
    }
}
