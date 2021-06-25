package pe.com.hiper.bmatic.perfilagendamientows.web.commands;


public class BookingTypeCommandDTO {
    private String id;
    private Integer schedulingId;
    private String bookingType;

    public Integer getSchedulingId() {
        return schedulingId;
    }

    public String getId() {
        return id;
    }

    public String getBookingType() {
        return bookingType;
    }
}
