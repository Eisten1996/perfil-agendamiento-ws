package pe.com.hiper.bmatic.perfilagendamientows.domain.schedule.model;

public class Schedule {

    private int id;
    private Integer schedulingId;
    private String counterTypeId;
    private String counterId;
    private String bookingType;
    private String startHour;
    private String endHour;
    private int day;
    private String date;
    private int addDating;

    public Schedule(int id, String counterTypeId, String startHour, String endHour, int day, String date,
                    String counterId, int addDating, String bookingType, Integer schedulingId) {
        this.id = id;
        this.counterTypeId = counterTypeId;
        this.startHour = startHour;
        this.endHour = endHour;
        this.day = day;
        this.date = date;
        this.counterId = counterId;
        this.addDating = addDating;
        this.bookingType = bookingType;
        this.schedulingId = schedulingId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCounterTypeId() {
        return counterTypeId;
    }

    public void setCounterTypeId(String counterTypeId) {
        this.counterTypeId = counterTypeId;
    }

    public String getStartHour() {
        return startHour;
    }

    public void setStartHour(String startHour) {
        this.startHour = startHour;
    }

    public String getEndHour() {
        return endHour;
    }

    public void setEndHour(String endHour) {
        this.endHour = endHour;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCounterId() {
        return counterId;
    }

    public void setCounterId(String counterId) {
        this.counterId = counterId;
    }

    public int getAddDating() {
        return addDating;
    }

    public void setAddDating(int addDating) {
        this.addDating = addDating;
    }

    public String getBookingType() {
        return bookingType;
    }

    public void setBookingType(String bookingType) {
        this.bookingType = bookingType;
    }

    public Integer getSchedulingId() {
        return schedulingId;
    }

    public void setSchedulingId(Integer schedulingId) {
        this.schedulingId = schedulingId;
    }

    public static ScheduleBuilder builder() {
        return new ScheduleBuilder();
    }

    public static class ScheduleBuilder {

        private int id;
        private String counterTypeId;
        private String startHour;
        private String endHour;
        private int day;
        private String counterId;
        private String date;
        private int addDating;
        private String bookingType;
        private Integer schedulingId;

        public ScheduleBuilder id(int id) {
            this.id = id;
            return this;
        }

        public ScheduleBuilder counterTypeId(String counterTypeId) {
            this.counterTypeId = counterTypeId;
            return this;
        }

        public ScheduleBuilder startHour(String startHour) {
            this.startHour = startHour;
            return this;
        }

        public ScheduleBuilder endHour(String endHour) {
            this.endHour = endHour;
            return this;
        }

        public ScheduleBuilder day(int day) {
            this.day = day;
            return this;
        }

        public ScheduleBuilder counterId(String counterId) {
            this.counterId = counterId;
            return this;
        }


        public ScheduleBuilder date(String date) {
            this.date = date;
            return this;
        }

        public ScheduleBuilder addDating(int addDating) {
            this.addDating = addDating;
            return this;
        }

        public ScheduleBuilder bookingType(String bookingType) {
            this.bookingType = bookingType;
            return this;
        }

        public ScheduleBuilder schedulingId(Integer schedulingId) {
            this.schedulingId = schedulingId;
            return this;
        }

        public Schedule build() {
            return new Schedule(id, counterTypeId, startHour, endHour, day, date, counterId, addDating,
                    bookingType, schedulingId);
        }
    }
}
