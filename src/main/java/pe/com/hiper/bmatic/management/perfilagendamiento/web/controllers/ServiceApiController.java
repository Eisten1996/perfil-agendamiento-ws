package pe.com.hiper.bmatic.management.perfilagendamiento.web.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pe.com.hiper.bmatic.management.perfilagendamiento.application.service.ServiceService;
import pe.com.hiper.bmatic.management.perfilagendamiento.domain.service.model.Service;
import pe.com.hiper.bmatic.management.perfilagendamiento.web.models.ServiceDTO;

import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@RestController
@RequestMapping("/v1/services")
public class ServiceApiController {
    private final Logger log = LogManager.getLogger(ServiceApiController.class);

    private final ServiceService serviceService;

    public ServiceApiController(ServiceService serviceService) {
        this.serviceService = serviceService;
    }

    @GetMapping
    public ResponseEntity<List<ServiceDTO>> getServices(
            @RequestParam(value = "branch_id", required = false) String branchId) {
        log.info("Obteniendo servicios para la agencia con código: " + branchId);
        List<ServiceDTO> serviceDTOS = new ArrayList<>();
        List<Service> services = serviceService.getListServicesByBranch(branchId);

        services.forEach((o) -> serviceDTOS.add(mapService(o)));
        return ResponseEntity.ok(serviceDTOS);
    }

    private ServiceDTO mapService(Service service) {
        return new ServiceDTO(service);
    }
}
