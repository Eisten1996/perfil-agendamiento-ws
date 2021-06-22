package pe.com.hiper.bmatic.perfilagendamientows.domain.branch.repository;

import pe.com.hiper.bmatic.perfilagendamientows.domain.branch.model.Branch;

import java.util.List;

public interface BranchRepository {
    List<Branch> getBranchListByUser(String userId, String branchId);
}
