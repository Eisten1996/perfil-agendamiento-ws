package pe.com.hiper.bmatic.perfilagendamientows.domain.counter.repository;

import pe.com.hiper.bmatic.perfilagendamientows.domain.counter.model.Counter;

import java.util.List;

public interface CounterRepository {

    List<Counter> getCounterList(String branchId);
}
