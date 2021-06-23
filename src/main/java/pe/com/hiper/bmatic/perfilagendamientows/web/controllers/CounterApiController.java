package pe.com.hiper.bmatic.perfilagendamientows.web.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import pe.com.hiper.bmatic.perfilagendamientows.application.counter.CounterService;
import pe.com.hiper.bmatic.perfilagendamientows.domain.branch.model.Branch;
import pe.com.hiper.bmatic.perfilagendamientows.domain.counter.model.Counter;
import pe.com.hiper.bmatic.perfilagendamientows.web.models.BranchDTO;
import pe.com.hiper.bmatic.perfilagendamientows.web.models.CounterDTO;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RestController
public class CounterApiController implements CounterApi {

    private final CounterService counterService;

    public CounterApiController(CounterService counterService) {
        this.counterService = counterService;
    }

    @Override
    public ResponseEntity<List<CounterDTO>> getListCounter(HttpServletRequest request) {
        List<CounterDTO> branchDTOS = new ArrayList<>();
        List<Counter> counters = counterService.getCounterList();

        counters.forEach((o) -> branchDTOS.add(mapCounter(o)));
        return ResponseEntity.ok(branchDTOS);
    }

    private CounterDTO mapCounter(Counter counter) {
        return new CounterDTO(counter);
    }
}
