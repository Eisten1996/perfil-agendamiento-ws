package pe.com.hiper.bmatic.perfilagendamientows.web.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import pe.com.hiper.bmatic.perfilagendamientows.application.asesor.AsesorService;
import pe.com.hiper.bmatic.perfilagendamientows.domain.asesor.model.Asesor;
import pe.com.hiper.bmatic.perfilagendamientows.web.models.AsesorDTO;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RestController
public class AsesorApiController implements AsesorApi {

    private final AsesorService asesorService;

    public AsesorApiController(AsesorService asesorService) {
        this.asesorService = asesorService;
    }

    @Override
    public ResponseEntity<List<AsesorDTO>> getListAsesor(HttpServletRequest request, String idTypeCounter, String branchId) {
        List<AsesorDTO> asesorDTOS = new ArrayList<>();
        List<Asesor> asesores = asesorService.getAsesorList(idTypeCounter, branchId);
        asesores.forEach(e -> asesorDTOS.add(mapAsesor(e)));
        return ResponseEntity.ok(asesorDTOS);
    }

    private AsesorDTO mapAsesor(Asesor asesor) {
        return new AsesorDTO(asesor);
    }
}
