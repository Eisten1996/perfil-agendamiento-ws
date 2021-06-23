package pe.com.hiper.bmatic.perfilagendamientows.web.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.com.hiper.bmatic.perfilagendamientows.domain.scheduling.model.Scheduling;
import pe.com.hiper.bmatic.perfilagendamientows.web.commands.CreateSchedulingCommandDTO;
import pe.com.hiper.bmatic.perfilagendamientows.web.models.SchedulingDTO;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


@RequestMapping("/v1/schedulings")
public interface SchedulingApi {

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<SchedulingDTO>> getSchedulings(HttpServletRequest request);

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Integer> saveScheduling(
            @RequestBody(required = true) CreateSchedulingCommandDTO command,
            HttpServletRequest request);

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping
    ResponseEntity deleteScheduling(@RequestParam(value = "scheduling_id") String scheduling_id,
                                    HttpServletRequest request);

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "getScheduling")
    ResponseEntity<SchedulingDTO> getScheduling(@RequestParam(value = "scheduling_id") String scheduling_id,
                                                HttpServletRequest request);
}
