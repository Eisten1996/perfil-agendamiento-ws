package pe.com.hiper.bmatic.management.perfilagendamiento.web.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.com.hiper.bmatic.management.perfilagendamiento.application.branch.BranchService;
import pe.com.hiper.bmatic.management.perfilagendamiento.application.service.ServiceService;
import pe.com.hiper.bmatic.management.perfilagendamiento.domain.branch.model.Branch;
import pe.com.hiper.bmatic.management.perfilagendamiento.domain.service.model.Service;
import pe.com.hiper.bmatic.management.perfilagendamiento.web.models.BranchDTO;
import pe.com.hiper.bmatic.management.perfilagendamiento.web.models.ServiceDTO;
import pe.com.hiper.hcenter.datossesion.entidad.UsuarioRest;
import javax.servlet.http.HttpSession;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v1/branches")
public class BranchApiController {

    private final HttpSession httpSession;

    private final BranchService branchService;
    private final ServiceService serviceService;

    public BranchApiController(BranchService branchService, ServiceService serviceService, HttpSession httpSession) {
        this.branchService = branchService;
        this.serviceService = serviceService;
        this.httpSession = httpSession;
    }

    @GetMapping
    ResponseEntity<List<BranchDTO>> getBranches() {

        List<BranchDTO> branchDTOS = new ArrayList<>();
        UsuarioRest user = (UsuarioRest) httpSession.getAttribute("usuarioActualControl");
        List<Branch> branches = branchService.getAllBranches(user.getCodigo());

        branches.forEach((o) -> {
            List<ServiceDTO> serviceDTOS = new ArrayList<>();
            List<Service> services = serviceService.getListServicesByBranch(o.getId());
            services.forEach((e) -> serviceDTOS.add(mapService(e)));
            branchDTOS.add(mapBranch(o, serviceDTOS));
        });
        return ResponseEntity.ok(branchDTOS);
    }

    private BranchDTO mapBranch(Branch branch, List<ServiceDTO> services) {
        return new BranchDTO(branch, services);
    }

    private ServiceDTO mapService(Service service) {
        return new ServiceDTO(service);
    }
}
