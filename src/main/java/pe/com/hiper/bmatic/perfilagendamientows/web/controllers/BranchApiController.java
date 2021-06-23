package pe.com.hiper.bmatic.perfilagendamientows.web.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import pe.com.hiper.bmatic.perfilagendamientows.application.branch.BranchService;
import pe.com.hiper.bmatic.perfilagendamientows.domain.branch.model.Branch;
import pe.com.hiper.bmatic.perfilagendamientows.web.models.BranchDTO;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RestController
public class BranchApiController implements BranchApi {

    private final BranchService branchService;

    public BranchApiController(BranchService branchService) {
        this.branchService = branchService;
    }

    @Override
    public ResponseEntity<List<BranchDTO>> getBranches(HttpServletRequest request, String userId, String branchId) {

        List<BranchDTO> branchDTOS = new ArrayList<>();
        List<Branch> branches = branchService.getAllBranches(userId, branchId);

        branches.forEach((o) -> branchDTOS.add(mapBranch(o)));
        return ResponseEntity.ok(branchDTOS);
    }

    private BranchDTO mapBranch(Branch branch) {
        return new BranchDTO(branch);
    }
}
