package pe.com.hiper.bmatic.perfilagendamientows.web.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import pe.com.hiper.bmatic.perfilagendamientows.application.shedule.CreateScheduleCommand;
import pe.com.hiper.bmatic.perfilagendamientows.application.shedule.ScheduleService;
import pe.com.hiper.bmatic.perfilagendamientows.domain.schedule.model.Schedule;
import pe.com.hiper.bmatic.perfilagendamientows.web.commands.CreateScheduleCommandDTO;
import pe.com.hiper.bmatic.perfilagendamientows.web.models.ScheduleDTO;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RestController
public class ScheduleApiController implements ScheduleApi {

    private final ScheduleService scheduleService;

    public ScheduleApiController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    @Override
    public ResponseEntity<Void> saveScheduleList(List<CreateScheduleCommandDTO> bodyList, String schedulingId, HttpServletRequest request) {
        List<CreateScheduleCommand> command = mapListCreateScheduleCommandDTO(bodyList);
        scheduleService.saveSchedules(command, Integer.parseInt(schedulingId));
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<List<ScheduleDTO>> getScheduleList(HttpServletRequest request, String schedulingId) {
        List<ScheduleDTO> scheduleDTOS = new ArrayList<>();
        List<Schedule> schedules = scheduleService.getListSchedules(Integer.parseInt(schedulingId));
        schedules.forEach(e -> scheduleDTOS.add(mapSchedule(e)));
        return ResponseEntity.ok(scheduleDTOS);
    }

    private List<CreateScheduleCommand> mapListCreateScheduleCommandDTO(List<CreateScheduleCommandDTO> bodyList) {
        List<CreateScheduleCommand> command = new ArrayList<>();
        bodyList.forEach(o -> {
            command.add(CreateScheduleCommand.builder()
                    .addDating(o.getAddDating())
                    .startHour(o.getStart())
                    .schedulingId(o.getSchedulingId())
                    .counterId(o.getCounterId())
                    .date(o.getDate())
                    .counterTypeId(o.getCounterTypeId())
                    .endHour(o.getEnd())
                    .day(o.getDay())
                    .bookingType(o.getBookingType())
                    .build());
        });
        return command;
    }

    private ScheduleDTO mapSchedule(Schedule schedule) {
        return new ScheduleDTO(schedule);
    }
}
