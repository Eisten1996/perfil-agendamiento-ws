package pe.com.hiper.bmatic.management.perfilagendamiento.infrastructure.scheduling;

import org.springframework.stereotype.Component;
import pe.com.hiper.bmatic.management.perfilagendamiento.domain.scheduling.model.Scheduling;
import pe.com.hiper.bmatic.management.perfilagendamiento.domain.scheduling.model.TypeScheduling;
import pe.com.hiper.bmatic.management.perfilagendamiento.domain.scheduling.repository.SchedulingRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

@Component
public class SchedulingRepositoryImpl implements SchedulingRepository {

    private final Logger log = LogManager.getLogger(SchedulingRepository.class);

    private final SchedulingJdbcClient jdbcClient;

    public SchedulingRepositoryImpl(SchedulingJdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    @Override
    public List<Scheduling> findAllSchedulings(String userId) {

        return jdbcClient.listSchedulings(userId);
    }

    @Override
    public Scheduling getSchedulingById(String schedulingId) {
        return jdbcClient.getScheduling(schedulingId);
    }

    @Override
    public Integer saveScheduling(Scheduling scheduling) {
        log.info("Código de perfil de agendamiento a guardar: " + Integer.toString(scheduling.getId()));
        Integer schedulingId = scheduling.getId();
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
    public void deleteCounterBookings(String branchId) {
        this.jdbcClient.deleteCounterBookings(branchId);
    }

    public void saveTypeScheduling(List<TypeScheduling> typeSchedulingList, String branchId) {
        jdbcClient.saveTypeScheduling(typeSchedulingList, branchId);
    }
}
