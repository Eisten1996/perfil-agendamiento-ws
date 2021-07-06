package pe.com.hiper.bmatic.management.perfilagendamiento.web.models;

import pe.com.hiper.bmatic.management.perfilagendamiento.domain.service.model.Service;

public class ServiceDTO {
    private String id;
    private String name;

    public ServiceDTO(Service service) {
        this.id = service.getId();
        this.name = service.getName();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
