package pe.com.hiper.bmatic.perfilagendamientows.infrastructure.agency;

import org.springframework.stereotype.Component;
import pe.com.hiper.bmatic.perfilagendamientows.domain.agency.model.Agency;
import pe.com.hiper.bmatic.perfilagendamientows.domain.agency.repository.AgencyRepository;

import java.util.List;

@Component
public class AgencyRepositoryImpl implements AgencyRepository {

    private final AgencyJdbcClient jdbcClient;

    public AgencyRepositoryImpl(AgencyJdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    @Override
    public List<Agency> getBranchListByUser(String userId, String branchId) {
        return jdbcClient.getBranchListByUser(userId, branchId);
    }
}
