package pe.com.hiper.bmatic.management.perfilagendamiento.web.models;

import java.util.ArrayList;
import java.util.List;

import pe.com.hiper.bmatic.management.perfilagendamiento.domain.asesor.model.Asesor;
import pe.com.hiper.bmatic.management.perfilagendamiento.domain.service.model.Service;

public class AsesorDTO {
    private String id;
    private String name;
    private List<ServiceDTO> services = new ArrayList<ServiceDTO>();

    public AsesorDTO(Asesor asesor) {
        this.id = asesor.getId();
        this.name = asesor.getName();
        asesor.getServices().forEach(s -> services.add(mapToDto(s)));
    }

    private ServiceDTO mapToDto(Service service) {
        return new ServiceDTO(service);
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
