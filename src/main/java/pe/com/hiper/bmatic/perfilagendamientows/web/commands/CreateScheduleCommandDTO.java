package pe.com.hiper.bmatic.perfilagendamientows.web.commands;

public class CreateScheduleCommandDTO {

    private String id;
    private Integer schedulingId;
    private String counterTypeId;
    private String counterId;
    private String bookingType;
    private String start;
    private String end;
    private int day;
    private String date;
    private int addDating;

    public String getId() {
        return id;
    }

    public Integer getSchedulingId() {
        return schedulingId;
    }

    public String getCounterTypeId() {
        return counterTypeId;
    }

    public String getCounterId() {
        return counterId;
    }

    public String getBookingType() {
        return bookingType;
    }

    public String getStart() {
        return start;
    }

    public String getEnd() {
        return end;
    }

    public int getDay() {
        return day;
    }

    public String getDate() {
        return date;
    }

    public int getAddDating() {
        return addDating;
    }
}
