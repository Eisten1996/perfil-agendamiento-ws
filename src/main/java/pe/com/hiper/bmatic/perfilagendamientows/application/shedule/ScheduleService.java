package pe.com.hiper.bmatic.perfilagendamientows.application.shedule;

import java.util.List;

public interface ScheduleService {

    public void saveSchedules(List<CreateScheduleCommand> commandList);
}
