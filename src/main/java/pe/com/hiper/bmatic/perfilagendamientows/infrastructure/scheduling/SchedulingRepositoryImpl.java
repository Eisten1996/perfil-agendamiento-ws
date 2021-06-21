package pe.com.hiper.bmatic.perfilagendamientows.infrastructure.scheduling;

import org.springframework.stereotype.Component;
import pe.com.hiper.bmatic.perfilagendamientows.domain.scheduling.model.Scheduling;
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
}
