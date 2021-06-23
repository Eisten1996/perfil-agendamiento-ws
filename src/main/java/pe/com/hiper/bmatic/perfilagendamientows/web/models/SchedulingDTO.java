package pe.com.hiper.bmatic.perfilagendamientows.web.models;

import pe.com.hiper.bmatic.perfilagendamientows.domain.scheduling.model.Scheduling;


public class SchedulingDTO {
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

    public SchedulingDTO(Scheduling scheduling) {
        this.id = scheduling.getId();
        this.branchId = scheduling.getBranchId();
        this.branchName = scheduling.getBranchName();
        this.minDays = scheduling.getMinDays();
        this.maxDays = scheduling.getMaxDays();
        this.toleranceTime = scheduling.getToleranceTime();
        this.services = scheduling.getServices();
        this.multipleBookings = scheduling.getMultipleBookings();
        this.confirmEmail = scheduling.getConfirmEmail();
        this.confirmTime = scheduling.getConfirmTime();
        this.unidConfirmTime = scheduling.getUnidConfirmTime();
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
}
