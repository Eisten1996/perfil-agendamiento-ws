package pe.com.hiper.bmatic.perfilagendamientows.application.asesor;

import org.springframework.stereotype.Component;
import pe.com.hiper.bmatic.perfilagendamientows.domain.asesor.model.Asesor;
import pe.com.hiper.bmatic.perfilagendamientows.domain.asesor.repository.AsesorRepository;

import java.util.List;

@Component
public class AsesorServiceImpl implements AsesorService {

    private final AsesorRepository asesorRepository;

    public AsesorServiceImpl(AsesorRepository asesorRepository) {
        this.asesorRepository = asesorRepository;
    }

    @Override
    public List<Asesor> getAsesorList(String idTypeCounter, String branchId) {
//        System.out.println("asesor" + asesorRepository.getAsesorList(idTypeCounter, branchId).get(0).getName());
        return asesorRepository.getAsesorList(idTypeCounter, branchId);
    }
}
