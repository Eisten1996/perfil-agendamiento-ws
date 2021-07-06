package pe.com.hiper.bmatic.management.perfilagendamiento.domain.counter.repository;

import pe.com.hiper.bmatic.management.perfilagendamiento.domain.counter.model.Counter;
import pe.com.hiper.bmatic.management.perfilagendamiento.domain.counter.model.TypeCounter;

import java.util.List;

public interface CounterRepository {

    List<TypeCounter> getTypeCounterList(String branchId);

    List<Counter> getCounterList(String idTypeCounter, String branchId);
}
