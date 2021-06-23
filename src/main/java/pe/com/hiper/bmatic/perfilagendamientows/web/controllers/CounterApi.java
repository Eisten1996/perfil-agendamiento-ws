package pe.com.hiper.bmatic.perfilagendamientows.web.controllers;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pe.com.hiper.bmatic.perfilagendamientows.web.models.CounterDTO;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RequestMapping("/v1/counters")
public interface CounterApi {

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<CounterDTO>> getListCounter(HttpServletRequest request);
}
