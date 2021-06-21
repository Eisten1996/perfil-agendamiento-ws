package pe.com.hiper.bmatic.perfilagendamientows.domain.agency.repository;

import pe.com.hiper.bmatic.perfilagendamientows.domain.agency.model.Agency;

import java.util.List;

public interface AgencyRepository {
    List<Agency> getBranchListByUser(String userId, String branchId);
}
