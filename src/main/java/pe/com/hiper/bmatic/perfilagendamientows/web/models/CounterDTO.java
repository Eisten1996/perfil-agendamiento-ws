package pe.com.hiper.bmatic.perfilagendamientows.web.models;

import pe.com.hiper.bmatic.perfilagendamientows.domain.counter.model.Counter;

public class CounterDTO {
    private String id;
    private String name;

    public CounterDTO(Counter counter) {
        this.id = counter.getId();
        this.name = counter.getName();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
