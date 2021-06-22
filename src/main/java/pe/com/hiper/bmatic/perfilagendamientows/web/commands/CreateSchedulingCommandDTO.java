package pe.com.hiper.bmatic.perfilagendamientows.web.commands;

public class CreateSchedulingCommandDTO {

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
}
