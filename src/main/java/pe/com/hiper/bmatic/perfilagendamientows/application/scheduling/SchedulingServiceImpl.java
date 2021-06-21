package pe.com.hiper.bmatic.perfilagendamientows.application.scheduling;

import org.springframework.stereotype.Component;
import pe.com.hiper.bmatic.perfilagendamientows.domain.scheduling.model.Scheduling;
import pe.com.hiper.bmatic.perfilagendamientows.domain.scheduling.repository.SchedulingRepository;

import java.util.List;

@Component
public class SchedulingServiceImpl implements SchedulingService {
    private final SchedulingRepository schedulingRepository;

    public SchedulingServiceImpl(SchedulingRepository schedulingRepository) {
        this.schedulingRepository = schedulingRepository;
    }

    @Override
    public List<Scheduling> getAllSchedulings() {
        return schedulingRepository.findAllSchedulings();
    }
}
