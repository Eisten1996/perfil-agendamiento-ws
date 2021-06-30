package pe.com.hiper.bmatic.perfilagendamientows.application.shedule;

import pe.com.hiper.bmatic.perfilagendamientows.domain.schedule.model.Schedule;

import java.util.List;

public interface ScheduleService {

    public void saveSchedules(List<CreateScheduleCommand> commandList);

    public List<Schedule> getListSchedules(Integer schedulingId);
}
