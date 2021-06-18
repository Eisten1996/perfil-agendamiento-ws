package pe.com.hiper.bmatic.perfilagendamientows.domain.agency.service;

import org.springframework.stereotype.Component;
import pe.com.hiper.bmatic.perfilagendamientows.domain.agency.model.Branch;
import pe.com.hiper.bmatic.perfilagendamientows.domain.agency.repository.BranchRepository;

import java.util.List;

@Component
public class BranchServiceImpl implements BranchService {
    private final BranchRepository branchRepository;

    public BranchServiceImpl(BranchRepository branchRepository) {
        this.branchRepository = branchRepository;
    }

    @Override
    public List<Branch> getAllBranches() {
        return branchRepository.findAllBranches();
    }
}
