package pe.com.hiper.bmatic.perfilagendamientows.application.shedule;

public class CreateScheduleCommand {

    private final int id;
    private final String schedulingId;
    private final String counterTypeId;
    private final String counterId;
    private final String bookingType;
    private final String startHour;
    private final String endHour;
    private final int day;
    private final String date;
    private final int addDating;


    public CreateScheduleCommand(int id, String schedulingId, String counterTypeId,
                                 String counterId, String bookingType, String startHour,
                                 String endHour, int day, String date, int addDating) {
        this.id = id;
        this.schedulingId = schedulingId;
        this.counterTypeId = counterTypeId;
        this.counterId = counterId;
        this.bookingType = bookingType;
        this.startHour = startHour;
        this.endHour = endHour;
        this.day = day;
        this.date = date;
        this.addDating = addDating;
    }

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

    public static CreateScheduleCommandBuilder builder() {
        return new CreateScheduleCommandBuilder();
    }

    public static class CreateScheduleCommandBuilder {
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

        CreateScheduleCommandBuilder() {
        }

        public CreateScheduleCommandBuilder id(final Integer id) {
            this.id = id;
            return this;
        }

        public CreateScheduleCommandBuilder schedulingId(final String schedulingId) {
            this.schedulingId = schedulingId;
            return this;
        }

        public CreateScheduleCommandBuilder counterTypeId(final String counterTypeId) {
            this.counterTypeId = counterTypeId;
            return this;
        }

        public CreateScheduleCommandBuilder counterId(final String counterId) {
            this.counterId = counterId;
            return this;
        }

        public CreateScheduleCommandBuilder bookingType(final String bookingType) {
            this.bookingType = bookingType;
            return this;
        }

        public CreateScheduleCommandBuilder startHour(final String startHour) {
            this.startHour = startHour;
            return this;
        }

        public CreateScheduleCommandBuilder endHour(final String endHour) {
            this.endHour = endHour;
            return this;
        }

        public CreateScheduleCommandBuilder day(final Integer day) {
            this.day = day;
            return this;
        }

        public CreateScheduleCommandBuilder date(final String date) {
            this.date = date;
            return this;
        }

        public CreateScheduleCommandBuilder addDating(final Integer addDating) {
            this.addDating = addDating;
            return this;
        }

        public CreateScheduleCommand build() {
            return new CreateScheduleCommand(id, schedulingId, counterTypeId, counterId, bookingType,
                    startHour, endHour, day, date, addDating);
        }

    }
}
