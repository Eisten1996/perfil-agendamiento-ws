package pe.com.hiper.bmatic.perfilagendamientows.web.models;

import pe.com.hiper.bmatic.perfilagendamientows.domain.scheduling.model.Scheduling;


public class SchedulingDTO {
    private int id;
    private String branchId;
    private String branchName;
    private int minDays;
    private int maxDays;

    public SchedulingDTO(Scheduling scheduling) {
        this.id = scheduling.getId();
        this.branchId = scheduling.getBranchId();
        this.branchName = scheduling.getBranchName();
        this.minDays = scheduling.getMinDays();
        this.maxDays = scheduling.getMaxDays();
    }

    public int getId() {
        return id;
    }

    public String getBranchId() {
        return branchId;
    }

    public String getBranchName() {
        return branchName;
    }

    public int getMinDays() {
        return minDays;
    }

    public int getMaxDays() {
        return maxDays;
    }

}
