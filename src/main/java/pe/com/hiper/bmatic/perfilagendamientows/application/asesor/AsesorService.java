package pe.com.hiper.bmatic.perfilagendamientows.application.asesor;

import pe.com.hiper.bmatic.perfilagendamientows.domain.asesor.model.Asesor;

import java.util.List;

public interface AsesorService {

    public List<Asesor> getAsesorList(String idTypeCounter,String branchId);
}
