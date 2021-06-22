package pe.com.hiper.bmatic.perfilagendamientows.web.models;

import pe.com.hiper.bmatic.perfilagendamientows.domain.branch.model.Branch;

public class BranchDTO {
    private String id;
    private String name;

    public BranchDTO(Branch branch) {
        this.id = branch.getId();
        this.name = branch.getName();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
