package pe.com.hiper.bmatic.management.perfilagendamiento.application.branch;

import pe.com.hiper.bmatic.management.perfilagendamiento.domain.branch.model.Branch;

import java.util.List;

public interface BranchService {
    public List<Branch> getAllBranches(String userId);
}
