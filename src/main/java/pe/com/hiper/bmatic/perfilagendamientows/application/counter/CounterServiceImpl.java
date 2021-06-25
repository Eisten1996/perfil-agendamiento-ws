package pe.com.hiper.bmatic.perfilagendamientows.application.counter;

import org.springframework.stereotype.Component;
import pe.com.hiper.bmatic.perfilagendamientows.domain.counter.model.Counter;
import pe.com.hiper.bmatic.perfilagendamientows.domain.counter.model.TypeCounter;
import pe.com.hiper.bmatic.perfilagendamientows.domain.counter.repository.CounterRepository;

import java.util.List;

@Component
public class CounterServiceImpl implements CounterService {

    private final CounterRepository counterRepository;

    public CounterServiceImpl(CounterRepository counterRepository) {
        this.counterRepository = counterRepository;
    }

    @Override
    public List<TypeCounter> getTypeCounterList(String branchId) {
        return counterRepository.getTypeCounterList(branchId);
    }

    @Override
    public List<Counter> getCounterList(String idTypeCounter, String branchId) {
        return counterRepository.getCounterList(idTypeCounter, branchId);
    }
}
