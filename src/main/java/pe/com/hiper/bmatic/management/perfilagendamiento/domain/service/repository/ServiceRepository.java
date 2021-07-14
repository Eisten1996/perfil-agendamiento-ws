package pe.com.hiper.bmatic.management.perfilagendamiento.domain.service.repository;

import pe.com.hiper.bmatic.management.perfilagendamiento.domain.service.model.Service;

import java.util.List;

public interface ServiceRepository {

    List<Service> getServicesByBranch(String branchId);

    List<Service> getServicesByAsesor(String asesorId);

    List<Service> getServicesByCounter(String counterId);
}
