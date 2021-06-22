package pe.com.hiper.bmatic.perfilagendamientows.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import pe.com.hiper.bmatic.perfilagendamientows.application.scheduling.CreateSchedulingCommand;
import pe.com.hiper.bmatic.perfilagendamientows.application.scheduling.SchedulingService;
import pe.com.hiper.bmatic.perfilagendamientows.domain.scheduling.model.Scheduling;
import pe.com.hiper.bmatic.perfilagendamientows.web.commands.CreateSchedulingCommandDTO;
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

    @Override
    public ResponseEntity<Integer> saveScheduling(CreateSchedulingCommandDTO body, HttpServletRequest request) {
        CreateSchedulingCommand command = CreateSchedulingCommand.builder()
                .id(body.getId())
                .branchId(body.getBranchId())
                .minDays(body.getMinDays())
                .maxDays(body.getMaxDays())
                .toleranceTime(body.getToleranceTime())
                .services(body.getServices())
                .multipleBookings(body.getMultipleBookings())
                .confirmEmail(body.getConfirmEmail())
                .confirmTime(body.getConfirmTime())
                .unidConfirmTime(body.getUnidConfirmTime())
                .build();

        return ResponseEntity.status(HttpStatus.CREATED).body(schedulingService.saveScheduling(command));
    }

    @Override
    public ResponseEntity deleteScheduling(String schedulingId, HttpServletRequest request) {
        return ResponseEntity.ok().body(schedulingService.deleteScheduling(schedulingId));
    }

    private SchedulingDTO mapBranch(Scheduling scheduling) {
        return new SchedulingDTO(scheduling);
    }
}
