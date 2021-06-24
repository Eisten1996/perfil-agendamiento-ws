package pe.com.hiper.bmatic.perfilagendamientows.web.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import pe.com.hiper.bmatic.perfilagendamientows.application.scheduling.CreateSchedulingCommand;
import pe.com.hiper.bmatic.perfilagendamientows.application.scheduling.CreateTypeSchedulingCommand;
import pe.com.hiper.bmatic.perfilagendamientows.application.scheduling.SchedulingService;
import pe.com.hiper.bmatic.perfilagendamientows.domain.scheduling.model.Scheduling;
import pe.com.hiper.bmatic.perfilagendamientows.web.commands.CreateSchedulingCommandDTO;
import pe.com.hiper.bmatic.perfilagendamientows.web.commands.CreateTypeSchedulingCommandDTO;
import pe.com.hiper.bmatic.perfilagendamientows.web.models.SchedulingDTO;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RestController
public class SchedulingApiController implements SchedulingApi {
    private final SchedulingService schedulingService;

    public SchedulingApiController(SchedulingService schedulingService) {
        this.schedulingService = schedulingService;
    }

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
    public ResponseEntity<Void> deleteScheduling(String scheduling_id, HttpServletRequest request) {
        schedulingService.deleteScheduling(scheduling_id);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<SchedulingDTO> getScheduling(String scheduling_id, HttpServletRequest request) {
        Scheduling scheduling = schedulingService.getScheduling(scheduling_id);
        SchedulingDTO schedulingDTO = mapBranch(scheduling);
        return ResponseEntity.ok(schedulingDTO);
    }

    @Override
    public ResponseEntity<Void> saveListTypeScheduling(CreateTypeSchedulingCommandDTO body, HttpServletRequest request) {
        List<CreateTypeSchedulingCommand> command = new ArrayList<>();
        body.getTypeSchedulingList().forEach(o -> {
            command.add(CreateTypeSchedulingCommand.builder()
                    .branchId(body.getBranchId())
                    .counterId(o.getCounterId())
                    .typeBooking(o.getTypeBooking())
                    .schedulingId(o.getSchedulingId())
                    .build());
        });
        schedulingService.saveTypeSchedulingList(command, body.getBranchId());
        return ResponseEntity.noContent().build();
    }

    private SchedulingDTO mapBranch(Scheduling scheduling) {
        return new SchedulingDTO(scheduling);
    }
}
