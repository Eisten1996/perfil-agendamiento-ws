package pe.com.hiper.bmatic.perfilagendamientows.domain.scheduling.repository;

import pe.com.hiper.bmatic.perfilagendamientows.domain.scheduling.model.Scheduling;

import java.util.List;

public interface SchedulingRepository {
    List<Scheduling> findAllSchedulings();

    Integer saveScheduling(Scheduling scheduling) throws Exception;
}
