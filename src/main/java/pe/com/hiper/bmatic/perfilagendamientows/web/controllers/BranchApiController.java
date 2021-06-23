package pe.com.hiper.bmatic.perfilagendamientows.web.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import pe.com.hiper.bmatic.perfilagendamientows.application.branch.BranchService;
import pe.com.hiper.bmatic.perfilagendamientows.application.service.ServiceService;
import pe.com.hiper.bmatic.perfilagendamientows.domain.branch.model.Branch;
import pe.com.hiper.bmatic.perfilagendamientows.domain.service.model.Service;
import pe.com.hiper.bmatic.perfilagendamientows.web.models.BranchDTO;
import pe.com.hiper.bmatic.perfilagendamientows.web.models.ServiceDTO;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RestController
public class BranchApiController implements BranchApi {

    private final BranchService branchService;
    private final ServiceService serviceService;

    public BranchApiController(BranchService branchService, ServiceService serviceService) {
        this.branchService = branchService;
        this.serviceService = serviceService;
    }

    @Override
    public ResponseEntity<List<BranchDTO>> getBranches(HttpServletRequest request, String userId, String branchId) {

        List<BranchDTO> branchDTOS = new ArrayList<>();
        List<Branch> branches = branchService.getAllBranches(userId, branchId);

        List<ServiceDTO> serviceDTOS = new ArrayList<>();
        List<Service> services = serviceService.getListServicesByBranch(branchId);

        services.forEach((o) -> serviceDTOS.add(mapService(o)));
        branches.forEach((o) -> branchDTOS.add(mapBranch(o, serviceDTOS)));
        return ResponseEntity.ok(branchDTOS);
    }

    private BranchDTO mapBranch(Branch branch, List<ServiceDTO> services) {
        return new BranchDTO(branch, services);
    }

    private ServiceDTO mapService(Service service) {
        return new ServiceDTO(service);
    }
}
