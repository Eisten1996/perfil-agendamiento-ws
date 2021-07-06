package pe.com.hiper.bmatic.management.perfilagendamiento.web.models;

import pe.com.hiper.bmatic.management.perfilagendamiento.domain.branch.model.Branch;

import java.util.List;

public class BranchDTO {
    private String id;
    private String name;
    private List<ServiceDTO> services;

    public BranchDTO(Branch branch, List<ServiceDTO> services) {
        this.id = branch.getId();
        this.name = branch.getName();
        this.services = services;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<ServiceDTO> getServices() {
        return services;
    }

}
