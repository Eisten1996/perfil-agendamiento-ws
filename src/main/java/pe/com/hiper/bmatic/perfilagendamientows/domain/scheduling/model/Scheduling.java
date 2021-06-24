package pe.com.hiper.bmatic.perfilagendamientows.domain.scheduling.model;

public class Scheduling {

    private Integer id;
    private String branchId;
    private String branchName;
    private Integer minDays;
    private Integer maxDays;
    private Integer toleranceTime;
    private String services;
    private Integer multipleBookings;
    private Integer confirmEmail;
    private Integer confirmTime;
    private String unidConfirmTime;
    private String typeAttention;

    public Scheduling(Integer id, String branchId, String branchName, Integer minDays, Integer maxDays,
                      Integer toleranceTime, String services, Integer multipleBookings, Integer confirmEmail,
                      Integer confirmTime, String unidConfirmTime, String typeAttention) {
        this.id = id;
        this.branchId = branchId;
        this.branchName = branchName;
        this.minDays = minDays;
        this.maxDays = maxDays;
        this.toleranceTime = toleranceTime;
        this.services = services;
        this.multipleBookings = multipleBookings;
        this.confirmEmail = confirmEmail;
        this.confirmTime = confirmTime;
        this.unidConfirmTime = unidConfirmTime;
        this.typeAttention = typeAttention;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBranchId() {
        return branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public Integer getMinDays() {
        return minDays;
    }

    public void setMinDays(Integer minDays) {
        this.minDays = minDays;
    }

    public Integer getMaxDays() {
        return maxDays;
    }

    public void setMaxDays(Integer maxDays) {
        this.maxDays = maxDays;
    }

    public Integer getToleranceTime() {
        return toleranceTime;
    }

    public void setToleranceTime(Integer toleranceTime) {
        this.toleranceTime = toleranceTime;
    }

    public String getServices() {
        return services;
    }

    public void setServices(String services) {
        this.services = services;
    }

    public Integer getMultipleBookings() {
        return multipleBookings;
    }

    public void setMultipleBookings(Integer multipleBookings) {
        this.multipleBookings = multipleBookings;
    }

    public Integer getConfirmEmail() {
        return confirmEmail;
    }

    public void setConfirmEmail(Integer confirmEmail) {
        this.confirmEmail = confirmEmail;
    }

    public Integer getConfirmTime() {
        return confirmTime;
    }

    public void setConfirmTime(Integer confirmTime) {
        this.confirmTime = confirmTime;
    }

    public String getUnidConfirmTime() {
        return unidConfirmTime;
    }

    public void setUnidConfirmTime(String unidConfirmTime) {
        this.unidConfirmTime = unidConfirmTime;
    }

    public String getTypeAttention() {
        return typeAttention;
    }

    public static SchedulingBuilder builder() {
        return new SchedulingBuilder();
    }

    public static class SchedulingBuilder {
        private Integer id;
        private String branchId;
        private String branchName;
        private Integer minDays;
        private Integer maxDays;
        private Integer toleranceTime;
        private String services;
        private Integer multipleBookings;
        private Integer confirmEmail;
        private Integer confirmTime;
        private String unidConfirmTime;
        private String typeAttention;

        public SchedulingBuilder id(Integer id) {
            this.id = id;
            return this;
        }

        public SchedulingBuilder branchId(String branchId) {
            this.branchId = branchId;
            return this;
        }

        public SchedulingBuilder branchName(String branchName) {
            this.branchName = branchName;
            return this;
        }

        public SchedulingBuilder minDays(Integer minDays) {
            this.minDays = minDays;
            return this;
        }

        public SchedulingBuilder maxDays(Integer maxDays) {
            this.maxDays = maxDays;
            return this;
        }

        public SchedulingBuilder toleranceTime(Integer toleranceTime) {
            this.toleranceTime = toleranceTime;
            return this;
        }

        public SchedulingBuilder services(String services) {
            this.services = services;
            return this;
        }

        public SchedulingBuilder multipleBookings(Integer multipleBookings) {
            this.multipleBookings = multipleBookings;
            return this;
        }

        public SchedulingBuilder confirmEmail(Integer confirmEmail) {
            this.confirmEmail = confirmEmail;
            return this;
        }

        public SchedulingBuilder confirmTime(Integer confirmTime) {
            this.confirmTime = confirmTime;
            return this;
        }

        public SchedulingBuilder unidConfirmTime(String unidConfirmTime) {
            this.unidConfirmTime = unidConfirmTime;
            return this;
        }

        public SchedulingBuilder typeAttention(String typeAttention) {
            this.typeAttention = typeAttention;
            return this;
        }

        public Scheduling build() {
            return new Scheduling(id, branchId, branchName, minDays, maxDays, toleranceTime, services,
                    multipleBookings, confirmEmail, confirmTime, unidConfirmTime, typeAttention);
        }
    }
}
