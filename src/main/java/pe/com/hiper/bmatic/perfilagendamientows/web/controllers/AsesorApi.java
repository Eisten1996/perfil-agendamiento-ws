package pe.com.hiper.bmatic.perfilagendamientows.web.controllers;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pe.com.hiper.bmatic.perfilagendamientows.web.models.AsesorDTO;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RequestMapping("/v1/asesors")
public interface AsesorApi {

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<AsesorDTO>> getListAsesor(HttpServletRequest request,
                                                  @RequestParam(value = "id_type_counter", required = false) String idTypeCounter,
                                                  @RequestParam(value = "branch_id", required = false) String branchId);
}
