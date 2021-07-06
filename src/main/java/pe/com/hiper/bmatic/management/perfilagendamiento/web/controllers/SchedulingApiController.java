package pe.com.hiper.bmatic.management.perfilagendamiento.web.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import pe.com.hiper.bmatic.management.perfilagendamiento.application.scheduling.CreateSchedulingCommand;
import pe.com.hiper.bmatic.management.perfilagendamiento.application.scheduling.CreateTypeSchedulingCommand;
import pe.com.hiper.bmatic.management.perfilagendamiento.application.scheduling.SchedulingService;
import pe.com.hiper.bmatic.management.perfilagendamiento.domain.scheduling.model.Scheduling;
import pe.com.hiper.bmatic.management.perfilagendamiento.web.commands.CreateSchedulingCommandDTO;
import pe.com.hiper.bmatic.management.perfilagendamiento.web.commands.CreateCounterBookingCommandDTO;
import pe.com.hiper.bmatic.management.perfilagendamiento.web.models.SchedulingDTO;
import pe.com.hiper.hcenter.datossesion.entidad.UsuarioRest;

import org.springframework.http.HttpStatus;
import javax.servlet.http.HttpSession;

import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@RestController
@RequestMapping("/v1/schedulings")
public class SchedulingApiController {

    private final Logger log = LogManager.getLogger(SchedulingApiController.class);

    private final HttpSession httpSession;
    private final SchedulingService schedulingService;

    public SchedulingApiController(SchedulingService schedulingService, HttpSession httpSession) {
        this.schedulingService = schedulingService;
        this.httpSession = httpSession;
    }

    @GetMapping
    public ResponseEntity<List<SchedulingDTO>> getSchedulings() {
        UsuarioRest user = (UsuarioRest) httpSession.getAttribute("usuarioActualControl");
        List<SchedulingDTO> schedulingDTOS = new ArrayList<>();
        List<Scheduling> schedulings = schedulingService.getAllSchedulings(user.getCodigo());

        schedulings.forEach((o) -> schedulingDTOS.add(mapBranch(o)));
        return ResponseEntity.ok(schedulingDTOS);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ResponseEntity<Integer> saveScheduling(@RequestBody CreateSchedulingCommandDTO body) {
        log.info("Guardando perfil de agendamiento de la agencia con código: " + body.getBranchId());
        CreateSchedulingCommand command = CreateSchedulingCommand.builder().id(body.getId())
                .branchId(body.getBranchId()).minDays(body.getMinDays()).maxDays(body.getMaxDays())
                .toleranceTime(body.getToleranceTime()).services(body.getServices())
                .multipleBookings(body.getMultipleBookings()).confirmEmail(body.getConfirmEmail())
                .confirmTime(body.getConfirmTime()).unidConfirmTime(body.getUnidConfirmTime()).build();

        return ResponseEntity.ok().body(schedulingService.saveScheduling(command));
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(value = "/{scheduling_id}")
    public ResponseEntity<Void> deleteScheduling(@PathVariable String scheduling_id) {
        schedulingService.deleteScheduling(scheduling_id);
        return ResponseEntity.noContent().build();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/{scheduling_id}")
    public ResponseEntity<SchedulingDTO> getScheduling(@PathVariable String scheduling_id) {
        Scheduling scheduling = schedulingService.getScheduling(scheduling_id);
        SchedulingDTO schedulingDTO = mapBranch(scheduling);
        return ResponseEntity.ok(schedulingDTO);
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping(value = "/saveListTypeScheduling")
    public ResponseEntity<Void> saveCounterBookingList(@RequestBody CreateCounterBookingCommandDTO body) {
        List<CreateTypeSchedulingCommand> command = new ArrayList<>();
        body.getBookingTypeList().forEach(o -> {
            command.add(CreateTypeSchedulingCommand.builder().branchId(body.getBranchId()).counterId(o.getId())
                    .typeBooking(o.getBookingType()).schedulingId(o.getSchedulingId()).build());
        });
        schedulingService.saveBookingTypeList(command, body.getBranchId());
        return ResponseEntity.noContent().build();
    }

    private SchedulingDTO mapBranch(Scheduling scheduling) {
        return new SchedulingDTO(scheduling);
    }
}
