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

    @Override
    public Integer saveScheduling(CreateSchedulingCommand command) {
        Scheduling scheduling = Scheduling.builder()
                .id(command.getId())
                .branchId(command.getBranchId())
                .minDays(command.getMinDays())
                .maxDays(command.getMaxDays())
                .toleranceTime(command.getToleranceTime())
                .services(command.getServices())
                .multipleBookings(command.getMultipleBookings())
                .confirmEmail(command.getConfirmEmail())
                .confirmTime(command.getConfirmTime())
                .unidConfirmTime(command.getUnidConfirmTime())
                .build();
        return schedulingRepository.saveScheduling(scheduling);

    }

    @Override
    public boolean deleteScheduling(String schedulingId) {
        schedulingRepository.deleteSchedulesById(schedulingId);
        schedulingRepository.deleteSchedulingById(schedulingId);
        return true;
    }

}
