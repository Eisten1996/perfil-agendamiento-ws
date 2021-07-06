package pe.com.hiper.bmatic.management.perfilagendamiento.web.models;

import pe.com.hiper.bmatic.management.perfilagendamiento.domain.counter.model.TypeCounter;

public class TypeCounterDTO {
    private String id;
    private String name;
    private String bookingType;

    public TypeCounterDTO(TypeCounter counter) {
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
