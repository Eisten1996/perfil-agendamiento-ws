package pe.com.hiper.bmatic.perfilagendamientows.web.models;

import pe.com.hiper.bmatic.perfilagendamientows.domain.counter.model.Counter;

public class CounterDTO {
    private String id;
    private String name;
    private String bookingType;

    public CounterDTO(Counter counter) {
        this.id = counter.getId();
        this.name = counter.getName();
        this.bookingType = counter.getBookingType();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getBookingType() {
        return bookingType;
    }
}
