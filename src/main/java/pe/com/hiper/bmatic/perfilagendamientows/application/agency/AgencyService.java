package pe.com.hiper.bmatic.perfilagendamientows.application.agency;

import pe.com.hiper.bmatic.perfilagendamientows.domain.agency.model.Agency;

import java.util.List;

public interface AgencyService {
    public List<Agency> getAllAgencies(String userId, String branchId);
}
