package pe.com.hiper.bmatic.management.perfilagendamiento.domain.scheduling.model;

public class TypeScheduling {
    private Integer schedulingId;
    private String branchId;
    private String counterId;
    private String typeBooking;

    public TypeScheduling(Integer schedulingId, String branchId, String counterId, String typeBooking) {
        this.schedulingId = schedulingId;
        this.branchId = branchId;
        this.counterId = counterId;
        this.typeBooking = typeBooking;
    }

    public Integer getSchedulingId() {
        return schedulingId;
    }

    public void setSchedulingId(Integer schedulingId) {
        this.schedulingId = schedulingId;
    }

    public String getBranchId() {
        return branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }

    public String getCounterId() {
        return counterId;
    }

    public void setCounterId(String counterId) {
        this.counterId = counterId;
    }

    public String getTypeBooking() {
        return typeBooking;
    }

    public void setTypeBooking(String typeBooking) {
        this.typeBooking = typeBooking;
    }

    public static TypeSchedulingBuilder builder() {
        return new TypeSchedulingBuilder();
    }

    public static class TypeSchedulingBuilder {
        private Integer schedulingId;
        private String branchId;
        private String counterId;
        private String typeBooking;

        public TypeScheduling.TypeSchedulingBuilder schedulingId(Integer schedulingId) {
            this.schedulingId = schedulingId;
            return this;
        }

        public TypeScheduling.TypeSchedulingBuilder branchId(String branchId) {
            this.branchId = branchId;
            return this;
        }

        public TypeScheduling.TypeSchedulingBuilder counterId(String counterId) {
            this.counterId = counterId;
            return this;
        }

        public TypeScheduling.TypeSchedulingBuilder typeBooking(String typeBooking) {
            this.typeBooking = typeBooking;
            return this;
        }

        public TypeScheduling build() {
            return new TypeScheduling(schedulingId, branchId, counterId, typeBooking);
        }
    }
}
