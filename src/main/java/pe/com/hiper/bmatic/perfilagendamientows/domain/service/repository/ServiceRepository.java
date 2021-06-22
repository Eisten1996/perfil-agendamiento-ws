package pe.com.hiper.bmatic.perfilagendamientows.domain.service.repository;

import pe.com.hiper.bmatic.perfilagendamientows.domain.service.model.Service;

import java.util.List;

public interface ServiceRepository {

    List<Service> getServicesByBranch(String branchId);
}
