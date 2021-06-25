package pe.com.hiper.bmatic.perfilagendamientows.web.controllers;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pe.com.hiper.bmatic.perfilagendamientows.web.models.CounterDTO;
import pe.com.hiper.bmatic.perfilagendamientows.web.models.TypeCounterDTO;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RequestMapping("/v1/counters")
public interface CounterApi {

    @GetMapping(value = "types", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<TypeCounterDTO>> getListTypeCounter(HttpServletRequest request,
                                                            @RequestParam(value = "branch_id", required = false) String branchId);

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<CounterDTO>> getListCounter(HttpServletRequest request,
                                                    @RequestParam(value = "id_type_counter", required = false) String idTypeCounter,
                                                    @RequestParam(value = "branch_id", required = false) String branchId);
}
