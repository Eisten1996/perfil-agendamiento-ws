package pe.com.hiper.bmatic.perfilagendamientows.application.counter;

import pe.com.hiper.bmatic.perfilagendamientows.domain.counter.model.Counter;
import pe.com.hiper.bmatic.perfilagendamientows.domain.counter.model.TypeCounter;

import java.util.List;

public interface CounterService {

    List<TypeCounter> getTypeCounterList(String branchId);

    List<Counter> getCounterList(String idTypeCounter, String branchId);
}
