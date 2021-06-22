package pe.com.hiper.bmatic.perfilagendamientows.application.scheduling;

import pe.com.hiper.bmatic.perfilagendamientows.domain.scheduling.model.Scheduling;

import java.util.List;

public interface SchedulingService {

    public List<Scheduling> getAllSchedulings();

    public Integer saveScheduling(CreateSchedulingCommand command) throws Exception;
}
