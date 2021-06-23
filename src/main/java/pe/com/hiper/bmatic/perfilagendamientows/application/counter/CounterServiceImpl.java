package pe.com.hiper.bmatic.perfilagendamientows.application.counter;

import org.springframework.stereotype.Component;
import pe.com.hiper.bmatic.perfilagendamientows.domain.counter.model.Counter;
import pe.com.hiper.bmatic.perfilagendamientows.domain.counter.model.CounterRepository;

import java.util.List;

@Component
public class CounterServiceImpl implements CounterService {

    private final CounterRepository counterRepository;

    public CounterServiceImpl(CounterRepository counterRepository) {
        this.counterRepository = counterRepository;
    }

    @Override
    public List<Counter> getCounterList() {
        return counterRepository.getCounterList();
    }
}
