package pe.com.hiper.bmatic.perfilagendamientows.web.controllers;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pe.com.hiper.bmatic.perfilagendamientows.web.models.SchedulingDTO;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


@RequestMapping("/v1/schedulings")
public interface SchedulingApi {

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<SchedulingDTO>> getSchedulings(HttpServletRequest request);
}
