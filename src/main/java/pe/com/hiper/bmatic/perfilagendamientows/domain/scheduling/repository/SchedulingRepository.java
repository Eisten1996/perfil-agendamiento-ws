package pe.com.hiper.bmatic.perfilagendamientows.domain.scheduling.repository;

import pe.com.hiper.bmatic.perfilagendamientows.domain.scheduling.model.Scheduling;
import pe.com.hiper.bmatic.perfilagendamientows.domain.scheduling.model.TypeScheduling;

import java.util.List;

public interface SchedulingRepository {
    List<Scheduling> findAllSchedulings();

    Scheduling getSchedulingById(String schedulingId);

    Integer saveScheduling(Scheduling scheduling);

    void deleteSchedulingById(Integer schedulingId);

    void deleteCounterBookings(String branchId);

    void saveTypeScheduling(List<TypeScheduling> typeSchedulingList, String branchId);

}
