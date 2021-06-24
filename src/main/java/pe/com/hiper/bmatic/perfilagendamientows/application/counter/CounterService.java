package pe.com.hiper.bmatic.perfilagendamientows.application.counter;

import pe.com.hiper.bmatic.perfilagendamientows.domain.counter.model.Counter;

import java.util.List;

public interface CounterService {

    List<Counter> getCounterList(String branchId);
}
