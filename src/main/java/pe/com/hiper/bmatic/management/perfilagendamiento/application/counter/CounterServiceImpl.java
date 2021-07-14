package pe.com.hiper.bmatic.management.perfilagendamiento.application.counter;

import org.springframework.stereotype.Component;
import pe.com.hiper.bmatic.management.perfilagendamiento.domain.counter.model.Counter;
import pe.com.hiper.bmatic.management.perfilagendamiento.domain.counter.model.TypeCounter;
import pe.com.hiper.bmatic.management.perfilagendamiento.domain.counter.repository.CounterRepository;
import pe.com.hiper.bmatic.management.perfilagendamiento.domain.service.repository.ServiceRepository;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CounterServiceImpl implements CounterService {

    private final CounterRepository counterRepository;
    private final ServiceRepository serviceRepository;

    public CounterServiceImpl(CounterRepository counterRepository, ServiceRepository serviceRepository) {
        this.counterRepository = counterRepository;
        this.serviceRepository = serviceRepository;
    }

    @Override
    public List<TypeCounter> getTypeCounterList(String branchId) {
        return counterRepository.getTypeCounterList(branchId);
    }

    @Override
    public List<Counter> getCounterList(String idTypeCounter, String branchId) {
        return counterRepository.getCounterList(idTypeCounter, branchId).stream()
        .map(a -> getServiceByCounter(a)).collect(Collectors.toList());
    }

    private Counter getServiceByCounter(Counter counter) {
        counter.setServices(this.serviceRepository.getServicesByCounter(counter.getId()));
        return counter;
    }
}
