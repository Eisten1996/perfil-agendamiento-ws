package pe.com.hiper.bmatic.perfilagendamientows.web.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import pe.com.hiper.bmatic.perfilagendamientows.web.commands.CreateScheduleCommandDTO;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RequestMapping("/v1/schedules")
public interface ScheduleApi {

    @ResponseStatus(HttpStatus.OK)
    @PostMapping()
    ResponseEntity<Void> saveScheduleList(
            @RequestBody List<CreateScheduleCommandDTO> commandList,
            HttpServletRequest request);
}
