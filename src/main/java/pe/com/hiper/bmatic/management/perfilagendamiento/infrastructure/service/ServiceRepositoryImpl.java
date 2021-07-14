package pe.com.hiper.bmatic.management.perfilagendamiento.infrastructure.service;

import org.springframework.stereotype.Component;
import pe.com.hiper.bmatic.management.perfilagendamiento.domain.service.model.Service;
import pe.com.hiper.bmatic.management.perfilagendamiento.domain.service.repository.ServiceRepository;
import pe.com.hiper.bmatic.management.perfilagendamiento.domain.shared.TypeSchedule;

import java.util.List;

@Component
public class ServiceRepositoryImpl implements ServiceRepository {

    private final ServiceJdbcClient jdbcClient;

    public ServiceRepositoryImpl(ServiceJdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    @Override
    public List<Service> getServicesByBranch(String branchId) {
        return jdbcClient.getServicesByBranch(branchId);
    }
    @Override
    public List<Service> getServicesByAsesor(String asesorId) {
        return jdbcClient.getServicesByType(TypeSchedule.ASESOR, asesorId);
    }
    @Override
    public List<Service> getServicesByCounter(String counterId) {
        return jdbcClient.getServicesByType(TypeSchedule.COUNTER, counterId);
    }
}
