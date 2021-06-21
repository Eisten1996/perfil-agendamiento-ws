package pe.com.hiper.bmatic.perfilagendamientows.application.agency;

import org.springframework.stereotype.Component;
import pe.com.hiper.bmatic.perfilagendamientows.domain.agency.model.Agency;
import pe.com.hiper.bmatic.perfilagendamientows.domain.agency.repository.AgencyRepository;

import java.util.List;

@Component
public class AgencyServiceImpl implements AgencyService {

    private final AgencyRepository agencyRepository;

    public AgencyServiceImpl(AgencyRepository agencyRepository) {
        this.agencyRepository = agencyRepository;
    }

    @Override
    public List<Agency> getAllAgencies(String userId, String branchId) {
        return agencyRepository.getBranchListByUser(userId, branchId);
    }
}
