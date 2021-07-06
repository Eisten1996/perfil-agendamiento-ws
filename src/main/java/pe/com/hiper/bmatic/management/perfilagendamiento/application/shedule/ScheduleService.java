package pe.com.hiper.bmatic.management.perfilagendamiento.application.shedule;

import pe.com.hiper.bmatic.management.perfilagendamiento.domain.schedule.model.Schedule;

import java.util.List;

public interface ScheduleService {

    public void saveSchedules(List<CreateScheduleCommand> commandList, Integer schedulingId);

    public List<Schedule> getListSchedules(Integer schedulingId);
}
