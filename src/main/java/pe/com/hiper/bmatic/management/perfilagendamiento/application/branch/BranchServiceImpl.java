package pe.com.hiper.bmatic.management.perfilagendamiento.application.branch;

import org.springframework.stereotype.Component;
import pe.com.hiper.bmatic.management.perfilagendamiento.domain.branch.model.Branch;
import pe.com.hiper.bmatic.management.perfilagendamiento.domain.branch.repository.BranchRepository;

import java.util.List;

@Component
public class BranchServiceImpl implements BranchService {

    private final BranchRepository branchRepository;

    public BranchServiceImpl(BranchRepository branchRepository) {
        this.branchRepository = branchRepository;
    }

    @Override
    public List<Branch> getAllBranches(String userId) {
        return branchRepository.getBranchListByUser(userId);
    }
}
