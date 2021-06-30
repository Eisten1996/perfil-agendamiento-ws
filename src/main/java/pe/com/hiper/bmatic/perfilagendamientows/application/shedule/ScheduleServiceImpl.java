package pe.com.hiper.bmatic.perfilagendamientows.application.shedule;

import org.springframework.stereotype.Component;
import pe.com.hiper.bmatic.perfilagendamientows.domain.schedule.model.Schedule;
import pe.com.hiper.bmatic.perfilagendamientows.domain.schedule.repository.ScheduleRepository;
import pe.com.hiper.bmatic.perfilagendamientows.domain.scheduling.model.TypeScheduling;

import java.util.ArrayList;
import java.util.List;

@Component
public class ScheduleServiceImpl implements ScheduleService {

    private final ScheduleRepository scheduleRepository;

    public ScheduleServiceImpl(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    @Override
    public void saveSchedules(List<CreateScheduleCommand> commandList) {
        List<Schedule> scheduleList = new ArrayList<>();
        if (!commandList.isEmpty()) {
            commandList.forEach(o -> {
                scheduleList.add(Schedule.builder()
                        .bookingType(o.getBookingType())
                        .day(o.getDay())
                        .schedulingId(o.getSchedulingId())
                        .addDating(o.getAddDating())
                        .date(o.getDate())
                        .counterId(o.getCounterId())
                        .counterTypeId(o.getCounterTypeId())
                        .startHour(o.getStartHour())
                        .endHour(o.getEndHour())
                        .build());
            });
        }
        scheduleRepository.saveSchedules(scheduleList);

    }
}
