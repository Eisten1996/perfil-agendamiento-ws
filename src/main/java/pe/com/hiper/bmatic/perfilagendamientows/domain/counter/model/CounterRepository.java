package pe.com.hiper.bmatic.perfilagendamientows.domain.counter.model;

import java.util.List;

public interface CounterRepository {

    List<Counter> getCounterList(String branchId);
}
