package pe.com.hiper.bmatic.perfilagendamientows.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import pe.com.hiper.bmatic.perfilagendamientows.domain.agency.model.Branch;
import pe.com.hiper.bmatic.perfilagendamientows.domain.agency.service.BranchService;
import pe.com.hiper.bmatic.perfilagendamientows.web.models.BranchDTO;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RestController
public class BranchApiController implements BranchApi {
    @Autowired
    BranchService branchService;

    @Override
    public ResponseEntity<List<BranchDTO>> getBranches(HttpServletRequest request) {
        List<BranchDTO> branchDTOS = new ArrayList<>();
        List<Branch> branches = branchService.getAllBranches();

        branches.forEach((o) -> branchDTOS.add(mapBranch(o)));
        return ResponseEntity.ok(branchDTOS);
    }

    private BranchDTO mapBranch(Branch branch) {
        return new BranchDTO(branch);
    }
}
