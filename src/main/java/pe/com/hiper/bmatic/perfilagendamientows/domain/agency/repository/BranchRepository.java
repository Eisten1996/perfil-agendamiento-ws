package pe.com.hiper.bmatic.perfilagendamientows.domain.agency.repository;

import pe.com.hiper.bmatic.perfilagendamientows.domain.agency.model.Branch;

import java.util.List;

public interface BranchRepository {
    List<Branch> findAllBranches();
}
