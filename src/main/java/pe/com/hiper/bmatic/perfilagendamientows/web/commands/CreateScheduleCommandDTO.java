package pe.com.hiper.bmatic.perfilagendamientows.web.commands;

public class CreateScheduleCommandDTO {

    private int id;
    private String schedulingId;
    private String counterTypeId;
    private String counterId;
    private String bookingType;
    private String startHour;
    private String endHour;
    private int day;
    private String date;
    private int addDating;

    public int getId() {
        return id;
    }

    public String getSchedulingId() {
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

    public String getStartHour() {
        return startHour;
    }

    public String getEndHour() {
        return endHour;
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
