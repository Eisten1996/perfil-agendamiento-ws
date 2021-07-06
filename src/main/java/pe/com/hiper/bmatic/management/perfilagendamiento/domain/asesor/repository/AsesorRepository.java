package pe.com.hiper.bmatic.management.perfilagendamiento.domain.asesor.repository;

import pe.com.hiper.bmatic.management.perfilagendamiento.domain.asesor.model.Asesor;

import java.util.List;

public interface AsesorRepository {
    List<Asesor> getAsesorList(String idTypeCounter, String branchId);
}
