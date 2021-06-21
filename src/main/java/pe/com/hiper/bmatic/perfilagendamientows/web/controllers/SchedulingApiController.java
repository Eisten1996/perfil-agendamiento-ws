package pe.com.hiper.bmatic.perfilagendamientows.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import pe.com.hiper.bmatic.perfilagendamientows.domain.scheduling.model.Scheduling;
import pe.com.hiper.bmatic.perfilagendamientows.application.scheduling.SchedulingService;
import pe.com.hiper.bmatic.perfilagendamientows.web.models.SchedulingDTO;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RestController
public class SchedulingApiController implements SchedulingApi {
    @Autowired
    SchedulingService schedulingService;

    @Override
    public ResponseEntity<List<SchedulingDTO>> getSchedulings(HttpServletRequest request) {
        List<SchedulingDTO> schedulingDTOS = new ArrayList<>();
        List<Scheduling> schedulings = schedulingService.getAllSchedulings();

        schedulings.forEach((o) -> schedulingDTOS.add(mapBranch(o)));
        return ResponseEntity.ok(schedulingDTOS);
    }

    private SchedulingDTO mapBranch(Scheduling scheduling) {
        return new SchedulingDTO(scheduling);
    }
}
