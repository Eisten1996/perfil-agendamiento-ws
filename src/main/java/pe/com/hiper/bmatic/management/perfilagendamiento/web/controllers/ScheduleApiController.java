package pe.com.hiper.bmatic.management.perfilagendamiento.web.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import pe.com.hiper.bmatic.management.perfilagendamiento.application.shedule.CreateScheduleCommand;
import pe.com.hiper.bmatic.management.perfilagendamiento.application.shedule.ScheduleService;
import pe.com.hiper.bmatic.management.perfilagendamiento.domain.schedule.model.Schedule;
import pe.com.hiper.bmatic.management.perfilagendamiento.web.commands.CreateScheduleCommandDTO;
import pe.com.hiper.bmatic.management.perfilagendamiento.web.models.ScheduleDTO;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import org.springframework.http.HttpStatus;

@RestController
@RequestMapping("/v1/schedules")
public class ScheduleApiController {

    private final ScheduleService scheduleService;

    public ScheduleApiController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping
    ResponseEntity<Void> saveScheduleList(@RequestBody List<CreateScheduleCommandDTO> commandList,
            @RequestParam(value = "scheduling_id", required = false) String schedulingId, HttpServletRequest request) {
        List<CreateScheduleCommand> command = mapListCreateScheduleCommandDTO(commandList);
        scheduleService.saveSchedules(command, Integer.parseInt(schedulingId));
        return ResponseEntity.noContent().build();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    ResponseEntity<List<ScheduleDTO>> getScheduleList(HttpServletRequest request,
            @RequestParam(value = "scheduling_id", required = false) String schedulingId) {
        List<ScheduleDTO> scheduleDTOS = new ArrayList<>();
        List<Schedule> schedules = scheduleService.getListSchedules(Integer.parseInt(schedulingId));
        schedules.forEach(e -> scheduleDTOS.add(mapSchedule(e)));
        return ResponseEntity.ok(scheduleDTOS);
    }

    private List<CreateScheduleCommand> mapListCreateScheduleCommandDTO(List<CreateScheduleCommandDTO> bodyList) {
        List<CreateScheduleCommand> command = new ArrayList<>();
        bodyList.forEach(o -> {
            command.add(CreateScheduleCommand.builder().addDating(o.getAddDating()).startHour(o.getStart())
                    .schedulingId(o.getSchedulingId()).counterId(o.getCounterId()).date(o.getDate())
                    .counterTypeId(o.getCounterTypeId()).endHour(o.getEnd()).day(o.getDay())
                    .bookingType(o.getBookingType()).build());
        });
        return command;
    }

    private ScheduleDTO mapSchedule(Schedule schedule) {
        return new ScheduleDTO(schedule);
    }
}
