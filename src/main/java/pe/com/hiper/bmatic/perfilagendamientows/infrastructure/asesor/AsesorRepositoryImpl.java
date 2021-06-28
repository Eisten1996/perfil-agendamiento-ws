package pe.com.hiper.bmatic.perfilagendamientows.infrastructure.asesor;

import org.springframework.stereotype.Component;
import pe.com.hiper.bmatic.perfilagendamientows.domain.asesor.model.Asesor;
import pe.com.hiper.bmatic.perfilagendamientows.domain.asesor.repository.AsesorRepository;

import java.util.List;

@Component
public class AsesorRepositoryImpl implements AsesorRepository {

    private final AsesorJdbcClient jdbcClient;

    public AsesorRepositoryImpl(AsesorJdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    @Override
    public List<Asesor> getAsesorList(String idTypeCounter, String branchId) {
        return jdbcClient.getAsesorList(idTypeCounter, branchId);
    }
}
