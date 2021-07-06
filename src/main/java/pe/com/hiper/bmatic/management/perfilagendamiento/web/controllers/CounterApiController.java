package pe.com.hiper.bmatic.management.perfilagendamiento.web.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pe.com.hiper.bmatic.management.perfilagendamiento.application.counter.CounterService;
import pe.com.hiper.bmatic.management.perfilagendamiento.domain.counter.model.Counter;
import pe.com.hiper.bmatic.management.perfilagendamiento.domain.counter.model.TypeCounter;
import pe.com.hiper.bmatic.management.perfilagendamiento.web.models.CounterDTO;
import pe.com.hiper.bmatic.management.perfilagendamiento.web.models.TypeCounterDTO;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v1/counters")
public class CounterApiController {

    private final CounterService counterService;

    public CounterApiController(CounterService counterService) {
        this.counterService = counterService;
    }

    @GetMapping(value = "types")
    ResponseEntity<List<TypeCounterDTO>> getListTypeCounter(HttpServletRequest request,
            @RequestParam(value = "branch_id", required = false) String branchId) {
        List<TypeCounterDTO> typeCounterDTOS = new ArrayList<>();
        List<TypeCounter> typeCounters = counterService.getTypeCounterList(branchId);

        typeCounters.forEach((o) -> typeCounterDTOS.add(mapTypeCounter(o)));
        return ResponseEntity.ok(typeCounterDTOS);
    }

    @GetMapping
    ResponseEntity<List<CounterDTO>> getListCounter(HttpServletRequest request,
            @RequestParam(value = "id_type_counter", required = false) String idTypeCounter,
            @RequestParam(value = "branch_id", required = false) String branchId) {

        List<CounterDTO> counterDTOS = new ArrayList<>();
        List<Counter> counters = counterService.getCounterList(idTypeCounter, branchId);

        counters.forEach((o) -> counterDTOS.add(mapCounter(o)));
        return ResponseEntity.ok(counterDTOS);
    }

    private TypeCounterDTO mapTypeCounter(TypeCounter typeCounter) {
        return new TypeCounterDTO(typeCounter);
    }

    private CounterDTO mapCounter(Counter counter) {
        return new CounterDTO(counter);
    }

}
