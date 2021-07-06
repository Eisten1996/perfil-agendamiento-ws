package pe.com.hiper.bmatic.management.perfilagendamiento.application.asesor;

import pe.com.hiper.bmatic.management.perfilagendamiento.domain.asesor.model.Asesor;

import java.util.List;

public interface AsesorService {

    public List<Asesor> getAsesorList(String idTypeCounter, String branchId);
}
