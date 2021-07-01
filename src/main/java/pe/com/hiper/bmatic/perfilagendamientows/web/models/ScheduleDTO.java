package pe.com.hiper.bmatic.perfilagendamientows.web.models;

import pe.com.hiper.bmatic.perfilagendamientows.domain.schedule.model.Schedule;

public class ScheduleDTO {
    private int id;
    private Integer schedulingId;
    private String counterTypeId;
    private String counterId;
    private String bookingType;
    private String start;
    private String end;
    private int day;
    private String date;
    private int addDating;

    public ScheduleDTO(Schedule schedule) {
        this.id = schedule.getId();
        this.schedulingId = schedule.getSchedulingId();
        this.counterTypeId = schedule.getCounterTypeId();
        this.counterId = schedule.getCounterId();
        this.bookingType = schedule.getBookingType();
        this.start = schedule.getStart();
        this.end = schedule.getEnd();
        this.day = schedule.getDay();
        this.date = schedule.getDate();
        this.addDating = schedule.getAddDating();
    }

    public int getId() {
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
