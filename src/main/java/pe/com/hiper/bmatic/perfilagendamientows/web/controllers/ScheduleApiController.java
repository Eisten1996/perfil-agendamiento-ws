package pe.com.hiper.bmatic.perfilagendamientows.web.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import pe.com.hiper.bmatic.perfilagendamientows.application.shedule.CreateScheduleCommand;
import pe.com.hiper.bmatic.perfilagendamientows.application.shedule.ScheduleService;
import pe.com.hiper.bmatic.perfilagendamientows.web.commands.CreateScheduleCommandDTO;

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
    public ResponseEntity<Void> saveScheduleList(List<CreateScheduleCommandDTO> bodyList, HttpServletRequest request) {
        List<CreateScheduleCommand> command = mapListCreateScheduleCommandDTO(bodyList);
        scheduleService.saveSchedules(command);
        return ResponseEntity.noContent().build();
    }

    private List<CreateScheduleCommand> mapListCreateScheduleCommandDTO(List<CreateScheduleCommandDTO> bodyList) {
        List<CreateScheduleCommand> command = new ArrayList<>();
        bodyList.forEach(o -> {
            command.add(CreateScheduleCommand.builder()
                    .addDating(o.getAddDating())
                    .startHour(o.getStartHour())
                    .schedulingId(o.getSchedulingId())
                    .counterId(o.getCounterId())
                    .date(o.getDate())
                    .counterTypeId(o.getCounterTypeId())
                    .endHour(o.getEndHour())
                    .day(o.getDay())
                    .bookingType(o.getBookingType())
                    .build());
        });
        return command;
    }
}
