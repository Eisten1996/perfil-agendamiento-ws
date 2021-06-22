package pe.com.hiper.bmatic.perfilagendamientows.infrastructure.service;

import org.springframework.stereotype.Component;
import pe.com.hiper.bmatic.perfilagendamientows.domain.service.model.Service;
import pe.com.hiper.bmatic.perfilagendamientows.domain.service.repository.ServiceRepository;

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
}
