package pe.com.hiper.bmatic.perfilagendamientows.web.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import pe.com.hiper.bmatic.perfilagendamientows.application.counter.CounterService;
import pe.com.hiper.bmatic.perfilagendamientows.domain.counter.model.Counter;
import pe.com.hiper.bmatic.perfilagendamientows.domain.counter.model.TypeCounter;
import pe.com.hiper.bmatic.perfilagendamientows.web.models.CounterDTO;
import pe.com.hiper.bmatic.perfilagendamientows.web.models.TypeCounterDTO;

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
    public ResponseEntity<List<TypeCounterDTO>> getListTypeCounter(HttpServletRequest request, String branchId) {
        List<TypeCounterDTO> typeCounterDTOS = new ArrayList<>();
        List<TypeCounter> typeCounters = counterService.getTypeCounterList(branchId);

        typeCounters.forEach((o) -> typeCounterDTOS.add(mapTypeCounter(o)));
        return ResponseEntity.ok(typeCounterDTOS);
    }

    @Override
    public ResponseEntity<List<CounterDTO>> getListCounter(HttpServletRequest request,
                                                           String idTypeCounter,
                                                           String branchId) {

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
