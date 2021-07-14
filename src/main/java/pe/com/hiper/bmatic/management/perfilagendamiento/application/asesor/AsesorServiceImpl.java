package pe.com.hiper.bmatic.management.perfilagendamiento.application.asesor;

import org.springframework.stereotype.Component;
import pe.com.hiper.bmatic.management.perfilagendamiento.domain.asesor.model.Asesor;
import pe.com.hiper.bmatic.management.perfilagendamiento.domain.asesor.repository.AsesorRepository;
import pe.com.hiper.bmatic.management.perfilagendamiento.domain.service.model.Service;
import pe.com.hiper.bmatic.management.perfilagendamiento.domain.service.repository.ServiceRepository;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AsesorServiceImpl implements AsesorService {

    private final AsesorRepository asesorRepository;
    private final ServiceRepository serviceRepository;

    public AsesorServiceImpl(AsesorRepository asesorRepository, ServiceRepository serviceRepository) {
        this.asesorRepository = asesorRepository;
        this.serviceRepository = serviceRepository;
    }

    @Override
    public List<Asesor> getAsesorList(String idTypeCounter, String branchId) {
        return 
        asesorRepository.getAsesorList(idTypeCounter, branchId)
        .stream().map(a -> getServiceByAsesor(a)).collect(Collectors.toList());
    }

    private Asesor getServiceByAsesor(Asesor asesor) {
        asesor.setServices(this.serviceRepository.getServicesByAsesor(asesor.getId()));
        return asesor;
    }
}
