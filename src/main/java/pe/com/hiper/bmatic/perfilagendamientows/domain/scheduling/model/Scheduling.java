package pe.com.hiper.bmatic.perfilagendamientows.domain.scheduling.model;

public class Scheduling {

    private int id;
    private String branchId;
    private String branchName;
    private int minDays;
    private int maxDays;

    public Scheduling(int id, String branchId, String branchName, int minDays, int maxDays) {
        this.id = id;
        this.branchId = branchId;
        this.branchName = branchName;
        this.minDays = minDays;
        this.maxDays = maxDays;
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

    public static SchedulingBuilder builder() {
        return new SchedulingBuilder();
    }

    public static class SchedulingBuilder {
        private int id;
        private String branchId;
        private String branchName;
        private int minDays;
        private int maxDays;

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

        public Scheduling build() {
            return new Scheduling(id, branchId, branchName, minDays, maxDays);
        }
    }
}
