package pe.com.hiper.bmatic.management.perfilagendamiento.domain.branch.repository;

import pe.com.hiper.bmatic.management.perfilagendamiento.domain.branch.model.Branch;

import java.util.List;

public interface BranchRepository {
    List<Branch> getBranchListByUser(String userId);
}
