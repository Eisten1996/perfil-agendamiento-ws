package pe.com.hiper.bmatic.perfilagendamientows.infrastructure.counter;

import org.springframework.stereotype.Component;
import pe.com.hiper.bmatic.perfilagendamientows.domain.counter.model.Counter;
import pe.com.hiper.bmatic.perfilagendamientows.domain.counter.model.TypeCounter;
import pe.com.hiper.bmatic.perfilagendamientows.domain.counter.repository.CounterRepository;

import java.util.List;

@Component
public class CounterRepositoryImpl implements CounterRepository {

    private final CounterJdbcClient jdbcClient;

    public CounterRepositoryImpl(CounterJdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    @Override
    public List<TypeCounter> getTypeCounterList(String branchId) {
        return jdbcClient.getTypeCounterList(branchId);
    }

    @Override
    public List<Counter> getCounterList(String idTypeCounter, String branchId) {
        return jdbcClient.getCounterList(idTypeCounter, branchId);
    }
}
