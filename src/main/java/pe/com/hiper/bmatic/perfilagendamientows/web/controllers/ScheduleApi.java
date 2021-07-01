package pe.com.hiper.bmatic.perfilagendamientows.web.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.com.hiper.bmatic.perfilagendamientows.web.commands.CreateScheduleCommandDTO;
import pe.com.hiper.bmatic.perfilagendamientows.web.models.ScheduleDTO;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RequestMapping("/v1/schedules")
public interface ScheduleApi {

    @ResponseStatus(HttpStatus.OK)
    @PostMapping()
    ResponseEntity<Void> saveScheduleList(
            @RequestBody List<CreateScheduleCommandDTO> commandList,
            @RequestParam(value = "scheduling_id", required = false) String scheduling_id,
            HttpServletRequest request);

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<ScheduleDTO>> getScheduleList(
            HttpServletRequest request,
            @RequestParam(value = "scheduling_id", required = false) String schedulingId);
}
