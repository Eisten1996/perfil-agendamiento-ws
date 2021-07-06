package pe.com.hiper.bmatic.management.perfilagendamiento.application.service;

import pe.com.hiper.bmatic.management.perfilagendamiento.domain.service.model.Service;

import java.util.List;

public interface ServiceService {
    List<Service> getListServicesByBranch(String branchId);
}
