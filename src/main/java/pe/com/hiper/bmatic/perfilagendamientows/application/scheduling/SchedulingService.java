package pe.com.hiper.bmatic.perfilagendamientows.application.scheduling;

import pe.com.hiper.bmatic.perfilagendamientows.domain.scheduling.model.Scheduling;

import java.util.List;

public interface SchedulingService {

    public List<Scheduling> getAllSchedulings();

    public Scheduling getScheduling(String schedulingId);

    public Integer saveScheduling(CreateSchedulingCommand command);

    public boolean deleteScheduling(String schedulingId);

    public int[] saveBookingTypeList(List<CreateTypeSchedulingCommand> commandList, String branchId);
}
