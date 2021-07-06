package pe.com.hiper.bmatic.management.perfilagendamiento.application.scheduling;

public class CreateTypeSchedulingCommand {

    private final Integer schedulingId;
    private final String branchId;
    private final String counterId;
    private final String typeBooking;

    public CreateTypeSchedulingCommand(Integer schedulingId, String branchId, String counterId, String typeBooking) {
        this.schedulingId = schedulingId;
        this.branchId = branchId;
        this.counterId = counterId;
        this.typeBooking = typeBooking;
    }

    public Integer getSchedulingId() {
        return schedulingId;
    }

    public String getBranchId() {
        return branchId;
    }

    public String getCounterId() {
        return counterId;
    }

    public String getTypeBooking() {
        return typeBooking;
    }

    public static CreateTypeSchedulingCommandBuilder builder() {
        return new CreateTypeSchedulingCommandBuilder();
    }

    public static class CreateTypeSchedulingCommandBuilder {
        private Integer schedulingId;
        private String branchId;
        private String counterId;
        private String typeBooking;

        CreateTypeSchedulingCommandBuilder() {

        }

        public CreateTypeSchedulingCommandBuilder schedulingId(final Integer schedulingId) {
            this.schedulingId = schedulingId;
            return this;
        }

        public CreateTypeSchedulingCommandBuilder branchId(final String branchId) {
            this.branchId = branchId;
            return this;
        }

        public CreateTypeSchedulingCommandBuilder counterId(final String counterId) {
            this.counterId = counterId;
            return this;
        }

        public CreateTypeSchedulingCommandBuilder typeBooking(final String typeBooking) {
            this.typeBooking = typeBooking;
            return this;
        }

        public CreateTypeSchedulingCommand build() {
            return new CreateTypeSchedulingCommand(schedulingId, branchId, counterId, typeBooking);
        }

    }
}
