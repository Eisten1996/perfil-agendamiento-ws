package pe.com.hiper.bmatic.perfilagendamientows.domain.scheduling.model;

public class Scheduling {

    private int id;
    private String branchId;
    private String branchName;
    private int minDays;
    private int maxDays;
    private int toleranceTime;
    private String services;
    private int multipleBookings;
    private int confirmEmail;
    private int confirmTime;
    private String unidConfirmTime;

    public Scheduling(int id, String branchId, String branchName, int minDays, int maxDays,
                      int toleranceTime, String services, int multipleBookings, int confirmEmail,
                      int confirmTime, String unidConfirmTime) {
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
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public int getMinDays() {
        return minDays;
    }

    public void setMinDays(int minDays) {
        this.minDays = minDays;
    }

    public int getMaxDays() {
        return maxDays;
    }

    public void setMaxDays(int maxDays) {
        this.maxDays = maxDays;
    }

    public int getToleranceTime() {
        return toleranceTime;
    }

    public void setToleranceTime(int toleranceTime) {
        this.toleranceTime = toleranceTime;
    }

    public String getServices() {
        return services;
    }

    public void setServices(String services) {
        this.services = services;
    }

    public int getMultipleBookings() {
        return multipleBookings;
    }

    public void setMultipleBookings(int multipleBookings) {
        this.multipleBookings = multipleBookings;
    }

    public int getConfirmEmail() {
        return confirmEmail;
    }

    public void setConfirmEmail(int confirmEmail) {
        this.confirmEmail = confirmEmail;
    }

    public int getConfirmTime() {
        return confirmTime;
    }

    public void setConfirmTime(int confirmTime) {
        this.confirmTime = confirmTime;
    }

    public String getUnidConfirmTime() {
        return unidConfirmTime;
    }

    public void setUnidConfirmTime(String unidConfirmTime) {
        this.unidConfirmTime = unidConfirmTime;
    }

    public static SchedulingBuilder builder() {
        return new SchedulingBuilder();
    }

    public static class SchedulingBuilder {
        private int id;
        private String branchId;
        private String branchName;
        private int minDays;
        private int maxDays;
        private int toleranceTime;
        private String services;
        private int multipleBookings;
        private int confirmEmail;
        private Integer confirmTime;
        private String unidConfirmTime;

        public SchedulingBuilder id(int id) {
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

        public SchedulingBuilder minDays(int minDays) {
            this.minDays = minDays;
            return this;
        }

        public SchedulingBuilder maxDays(int maxDays) {
            this.maxDays = maxDays;
            return this;
        }

        public SchedulingBuilder toleranceTime(int toleranceTime) {
            this.toleranceTime = toleranceTime;
            return this;
        }

        public SchedulingBuilder services(String services) {
            this.services = services;
            return this;
        }

        public SchedulingBuilder multipleBookings(int multipleBookings) {
            this.multipleBookings = multipleBookings;
            return this;
        }

        public SchedulingBuilder confirmEmail(int confirmEmail) {
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

        public Scheduling build() {
            return new Scheduling(id, branchId, branchName, minDays, maxDays, toleranceTime, services,
                    multipleBookings, confirmEmail, confirmTime, unidConfirmTime);
        }
    }
}
