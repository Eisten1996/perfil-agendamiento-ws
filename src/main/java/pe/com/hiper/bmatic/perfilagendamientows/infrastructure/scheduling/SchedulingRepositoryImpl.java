package pe.com.hiper.bmatic.perfilagendamientows.infrastructure.scheduling;

import org.springframework.stereotype.Component;
import pe.com.hiper.bmatic.perfilagendamientows.domain.scheduling.model.Scheduling;
import pe.com.hiper.bmatic.perfilagendamientows.domain.scheduling.model.TypeScheduling;
import pe.com.hiper.bmatic.perfilagendamientows.domain.scheduling.repository.SchedulingRepository;

import java.util.List;

@Component
public class SchedulingRepositoryImpl implements SchedulingRepository {

    private final SchedulingJdbcClient jdbcClient;

    public SchedulingRepositoryImpl(SchedulingJdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    @Override
    public List<Scheduling> findAllSchedulings() {

        return jdbcClient.listSchedulings();
    }

    @Override
    public Scheduling getSchedulingById(String schedulingId) {
        return jdbcClient.getScheduling(schedulingId);
    }

    @Override
    public Integer saveScheduling(Scheduling scheduling) {
        int schedulingId = scheduling.getId();
        boolean save = false;
        if (schedulingId != 0) {
            save = true;
        } else if (!jdbcClient.existsScheduling(scheduling.getBranchId())) {
            save = true;
        }
        if (save) {
            schedulingId = jdbcClient.saveScheduling(scheduling);
        }
        return schedulingId;
    }

    @Override
    public void deleteSchedulingById(Integer schedulingId) {
        this.jdbcClient.deleteSchedulingById(schedulingId);
    }

    @Override
    public void deleteSchedulesById(Integer schedulingId) {
        this.jdbcClient.deleteTypeSchedules(schedulingId);
    }

    @Override
    public void deleteCounterBookings(String branchId) {
        this.jdbcClient.deleteCounterBookings(branchId);
    }

    public int[] saveTypeScheduling(List<TypeScheduling> typeSchedulingList, String branchId) {
        return jdbcClient.saveTypeScheduling(typeSchedulingList, branchId);
    }
}
