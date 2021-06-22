package pe.com.hiper.bmatic.perfilagendamientows.application.branch;

import pe.com.hiper.bmatic.perfilagendamientows.domain.branch.model.Branch;

import java.util.List;

public interface BranchService {
    public List<Branch> getAllAgencies(String userId, String branchId);
}
