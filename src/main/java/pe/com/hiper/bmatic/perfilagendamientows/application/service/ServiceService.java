package pe.com.hiper.bmatic.perfilagendamientows.application.service;

import pe.com.hiper.bmatic.perfilagendamientows.domain.service.model.Service;

import java.util.List;

public interface ServiceService {
    List<Service> getListServicesByBranch(String branchId);
}
