package pe.com.hiper.bmatic.management.perfilagendamiento.infrastructure.branch;

import org.springframework.stereotype.Component;
import pe.com.hiper.bmatic.management.perfilagendamiento.domain.branch.model.Branch;
import pe.com.hiper.bmatic.management.perfilagendamiento.domain.branch.repository.BranchRepository;

import java.util.List;

@Component
public class BranchRepositoryImpl implements BranchRepository {

    private final BranchJdbcClient jdbcClient;

    public BranchRepositoryImpl(BranchJdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    @Override
    public List<Branch> getBranchListByUser(String userId) {
        return jdbcClient.getBranchListByUser(userId);
    }
}
