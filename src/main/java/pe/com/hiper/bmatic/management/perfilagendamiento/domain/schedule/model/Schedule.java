package pe.com.hiper.bmatic.management.perfilagendamiento.domain.schedule.model;

public class Schedule {

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

    public Schedule(int id, String counterTypeId, String start, String end, int day, String date, String counterId,
            int addDating, String bookingType, Integer schedulingId) {
        this.id = id;
        this.counterTypeId = counterTypeId;
        this.start = start;
        this.end = end;
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

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEndHour(String end) {
        this.end = end;
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
        private String start;
        private String end;
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

        public ScheduleBuilder start(String start) {
            this.start = start;
            return this;
        }

        public ScheduleBuilder end(String end) {
            this.end = end;
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
            return new Schedule(id, counterTypeId, start, end, day, date, counterId, addDating, bookingType,
                    schedulingId);
        }
    }
}
