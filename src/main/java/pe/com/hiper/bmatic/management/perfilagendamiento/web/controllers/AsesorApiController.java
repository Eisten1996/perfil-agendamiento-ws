package pe.com.hiper.bmatic.management.perfilagendamiento.web.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pe.com.hiper.bmatic.management.perfilagendamiento.application.asesor.AsesorService;
import pe.com.hiper.bmatic.management.perfilagendamiento.domain.asesor.model.Asesor;
import pe.com.hiper.bmatic.management.perfilagendamiento.web.models.AsesorDTO;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v1/asesors")
public class AsesorApiController {

    private final AsesorService asesorService;

    public AsesorApiController(AsesorService asesorService) {
        this.asesorService = asesorService;
    }

    @GetMapping
    ResponseEntity<List<AsesorDTO>> getListAsesor(
            @RequestParam(value = "id_type_counter", required = false) String idTypeCounter,
            @RequestParam(value = "branch_id", required = false) String branchId) {
        List<AsesorDTO> asesorDTOS = new ArrayList<>();
        List<Asesor> asesores = asesorService.getAsesorList(idTypeCounter, branchId);
        asesores.forEach(e -> asesorDTOS.add(mapAsesor(e)));
        return ResponseEntity.ok(asesorDTOS);
    }

    private AsesorDTO mapAsesor(Asesor asesor) {
        return new AsesorDTO(asesor);
    }
}
