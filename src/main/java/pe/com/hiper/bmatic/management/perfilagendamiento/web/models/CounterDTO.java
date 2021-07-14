package pe.com.hiper.bmatic.management.perfilagendamiento.web.models;

import java.util.ArrayList;
import java.util.List;
import pe.com.hiper.bmatic.management.perfilagendamiento.domain.counter.model.Counter;
import pe.com.hiper.bmatic.management.perfilagendamiento.domain.service.model.Service;

public class CounterDTO {
    private String id;
    private String name;
    private List<ServiceDTO> services = new ArrayList<ServiceDTO>();

    public CounterDTO(Counter counter) {
        this.id = counter.getId();
        this.name = counter.getName();
        counter.getServices().forEach(s -> services.add(mapToDto(s)));
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
}
