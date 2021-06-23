package pe.com.hiper.bmatic.perfilagendamientows.domain.scheduling.repository;

import pe.com.hiper.bmatic.perfilagendamientows.domain.scheduling.model.Scheduling;

import java.util.List;

public interface SchedulingRepository {
    List<Scheduling> findAllSchedulings();

    Scheduling getSchedulingById(String schedulingId);

    Integer saveScheduling(Scheduling scheduling);

    void deleteSchedulingById(String schedulingId);

    void deleteSchedulesById(String schedulingId);


}
