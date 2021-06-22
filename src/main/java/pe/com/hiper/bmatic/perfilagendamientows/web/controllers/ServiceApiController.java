package pe.com.hiper.bmatic.perfilagendamientows.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import pe.com.hiper.bmatic.perfilagendamientows.application.service.ServiceService;
import pe.com.hiper.bmatic.perfilagendamientows.domain.branch.model.Branch;
import pe.com.hiper.bmatic.perfilagendamientows.domain.service.model.Service;
import pe.com.hiper.bmatic.perfilagendamientows.web.models.BranchDTO;
import pe.com.hiper.bmatic.perfilagendamientows.web.models.ServiceDTO;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RestController
public class ServiceApiController implements ServiceApi {

    @Autowired
    ServiceService serviceService;

    @Override
    public ResponseEntity<List<ServiceDTO>> getServices(HttpServletRequest request, String branchId) {

        List<ServiceDTO> serviceDTOS = new ArrayList<>();
        List<Service> services = serviceService.getListServicesByBranch(branchId);

        services.forEach((o) -> serviceDTOS.add(mapService(o)));
        return ResponseEntity.ok(serviceDTOS);
    }

    private ServiceDTO mapService(Service service) {
        return new ServiceDTO(service);
    }
}
