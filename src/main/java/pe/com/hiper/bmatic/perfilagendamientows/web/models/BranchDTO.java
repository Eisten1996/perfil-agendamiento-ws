package pe.com.hiper.bmatic.perfilagendamientows.web.models;

import pe.com.hiper.bmatic.perfilagendamientows.domain.agency.model.Branch;


public class BranchDTO {
    private String codProfile;
    private String nameBranch;
    private int minDays;
    private int maxDays;

    public BranchDTO(Branch branch) {
        this.codProfile = branch.getId();
        this.nameBranch = branch.getNameBranch();
        this.minDays = branch.getMinDayScheduler();
        this.maxDays = branch.getMaxDayScheduler();
    }


    public String getCodProfile() {
        return codProfile;
    }

    public String getNameBranch() {
        return nameBranch;
    }

    public int getMinDays() {
        return minDays;
    }

    public int getMaxDays() {
        return maxDays;
    }
}
