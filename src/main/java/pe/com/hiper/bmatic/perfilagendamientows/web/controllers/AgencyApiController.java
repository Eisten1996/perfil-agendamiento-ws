package pe.com.hiper.bmatic.perfilagendamientows.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import pe.com.hiper.bmatic.perfilagendamientows.application.agency.AgencyService;
import pe.com.hiper.bmatic.perfilagendamientows.domain.agency.model.Agency;
import pe.com.hiper.bmatic.perfilagendamientows.web.models.AgencyDTO;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RestController
public class AgencyApiController implements AgencyApi {

    @Autowired
    AgencyService agencyService;

    @Override
    public ResponseEntity<List<AgencyDTO>> getAgencies(HttpServletRequest request, String userId, String branchId) {

        List<AgencyDTO> agencyDTOS = new ArrayList<>();
        List<Agency> agencies = agencyService.getAllAgencies(userId, branchId);

        agencies.forEach((o) -> agencyDTOS.add(mapAgency(o)));
        return ResponseEntity.ok(agencyDTOS);
    }

    private AgencyDTO mapAgency(Agency agency) {
        return new AgencyDTO(agency);
    }
}
