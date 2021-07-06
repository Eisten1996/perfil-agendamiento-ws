package pe.com.hiper.bmatic.management.perfilagendamiento.application.scheduling;

import pe.com.hiper.bmatic.management.perfilagendamiento.domain.scheduling.model.Scheduling;

import java.util.List;

public interface SchedulingService {

    public List<Scheduling> getAllSchedulings(String userId);

    public Scheduling getScheduling(String schedulingId);

    public Integer saveScheduling(CreateSchedulingCommand command);

    public boolean deleteScheduling(String schedulingId);

    public void saveBookingTypeList(List<CreateTypeSchedulingCommand> commandList, String branchId);
}
