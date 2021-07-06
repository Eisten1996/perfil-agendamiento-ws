package pe.com.hiper.bmatic.management.perfilagendamiento.web.commands;

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

    public CreateSchedulingCommandDTO() {
        
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


    
    public void setId(Integer id) {
        this.id = id;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }

    public void setMinDays(Integer minDays) {
        this.minDays = minDays;
    }

    public void setMaxDays(Integer maxDays) {
        this.maxDays = maxDays;
    }

    public void setToleranceTime(Integer toleranceTime) {
        this.toleranceTime = toleranceTime;
    }

    public void setServices(String services) {
        this.services = services;
    }

    public void setMultipleBookings(Integer multipleBookings) {
        this.multipleBookings = multipleBookings;
    }

    public void setConfirmEmail(Integer confirmEmail) {
        this.confirmEmail = confirmEmail;
    }

    public void setConfirmTime(Integer confirmTime) {
        this.confirmTime = confirmTime;
    }

    public void setUnidConfirmTime(String unidConfirmTime) {
        this.unidConfirmTime = unidConfirmTime;
    }

    @Override
    public String toString() {
        return "CreateSchedulingCommandDTO [branchId=" + branchId + ", confirmEmail=" + confirmEmail + ", confirmTime="
                + confirmTime + ", id=" + id + ", maxDays=" + maxDays + ", minDays=" + minDays + ", multipleBookings="
                + multipleBookings + ", services=" + services + ", toleranceTime=" + toleranceTime
                + ", unidConfirmTime=" + unidConfirmTime + "]";
    }

    
}
