package pe.com.hiper.bmatic.perfilagendamientows.application.service;

import org.springframework.stereotype.Component;
import pe.com.hiper.bmatic.perfilagendamientows.domain.service.model.Service;
import pe.com.hiper.bmatic.perfilagendamientows.domain.service.repository.ServiceRepository;

import java.util.List;

@Component
public class ServiceServiceImpl implements ServiceService {

    private final ServiceRepository serviceRepository;

    public ServiceServiceImpl(ServiceRepository serviceRepository) {
        this.serviceRepository = serviceRepository;
    }

    @Override
    public List<Service> getListServicesByBranch(String branchId) {
        return serviceRepository.getServicesByBranch(branchId);
    }
}
