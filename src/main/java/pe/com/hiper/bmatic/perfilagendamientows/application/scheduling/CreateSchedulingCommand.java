package pe.com.hiper.bmatic.perfilagendamientows.application.scheduling;

public class CreateSchedulingCommand {
    private final Integer id;
    private final String branchId;
    private final Integer minDays;
    private final Integer maxDays;
    private final Integer toleranceTime;
    private final String services;
    private final Integer multipleBookings;
    private final Integer confirmEmail;
    private final Integer confirmTime;
    private final String unidConfirmTime;

    public CreateSchedulingCommand(Integer id, String branchId, Integer minDays, Integer maxDays,
                                   Integer toleranceTime, String services, Integer multipleBookings,
                                   Integer confirmEmail, Integer confirmTime, String unidConfirmTime) {
        this.id = id;
        this.branchId = branchId;
        this.minDays = minDays;
        this.maxDays = maxDays;
        this.toleranceTime = toleranceTime;
        this.services = services;
        this.multipleBookings = multipleBookings;
        this.confirmEmail = confirmEmail;
        this.confirmTime = confirmTime;
        this.unidConfirmTime = unidConfirmTime;
    }

    public Integer getId() {
        return id;
    }

    public String getBranchId() {
        return branchId;
    }

    public Integer getMinDays() {
        return minDays;
    }

    public Integer getMaxDays() {
        return maxDays;
    }

    public Integer getToleranceTime() {
        return toleranceTime;
    }

    public String getServices() {
        return services;
    }

    public Integer getMultipleBookings() {
        return multipleBookings;
    }

    public Integer getConfirmEmail() {
        return confirmEmail;
    }

    public Integer getConfirmTime() {
        return confirmTime;
    }

    public String getUnidConfirmTime() {
        return unidConfirmTime;
    }

    public static CreateSchedulingCommandBuilder builder() {
        return new CreateSchedulingCommandBuilder();
    }

    public static class CreateSchedulingCommandBuilder {
        private Integer id;
        private String branchId;
        private Integer minDays;
        private Integer maxDays;
        private Integer toleranceTime;
        private String services;
        private Integer multipleBookings;
        private Integer confirmEmail;
        private Integer confirmTime;
        private String unidConfirmTime;

        CreateSchedulingCommandBuilder() {

        }

        public CreateSchedulingCommandBuilder id(final Integer id) {
            this.id = id;
            return this;
        }

        public CreateSchedulingCommandBuilder branchId(final String branchId) {
            this.branchId = branchId;
            return this;
        }

        public CreateSchedulingCommandBuilder minDays(final Integer minDays) {
            this.minDays = minDays;
            return this;
        }

        public CreateSchedulingCommandBuilder maxDays(final Integer maxDays) {
            this.maxDays = maxDays;
            return this;
        }

        public CreateSchedulingCommandBuilder toleranceTime(final Integer toleranceTime) {
            this.toleranceTime = toleranceTime;
            return this;
        }

        public CreateSchedulingCommandBuilder services(final String services) {
            this.services = services;
            return this;
        }

        public CreateSchedulingCommandBuilder multipleBookings(final Integer multipleBookings) {
            this.multipleBookings = multipleBookings;
            return this;
        }

        public CreateSchedulingCommandBuilder confirmEmail(final Integer confirmEmail) {
            this.confirmEmail = confirmEmail;
            return this;
        }

        public CreateSchedulingCommandBuilder confirmTime(final Integer confirmTime) {
            this.confirmTime = confirmTime;
            return this;
        }

        public CreateSchedulingCommandBuilder unidConfirmTime(final String unidConfirmTime) {
            this.unidConfirmTime = unidConfirmTime;
            return this;
        }

        public CreateSchedulingCommand build() {
            return new CreateSchedulingCommand(id, branchId, minDays, maxDays, toleranceTime, services,
                    multipleBookings, confirmEmail, confirmTime, unidConfirmTime);
        }

    }
}
