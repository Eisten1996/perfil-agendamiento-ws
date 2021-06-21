package pe.com.hiper.bmatic.perfilagendamientows.web.controllers;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pe.com.hiper.bmatic.perfilagendamientows.web.models.AgencyDTO;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RequestMapping("/v1/agency")
public interface AgencyApi {

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<AgencyDTO>> getAgencies(HttpServletRequest request,
                                                @RequestParam(value = "user_id", required = false) String userId,
                                                @RequestParam(value = "branch_id", required = false) String branchId);
}
