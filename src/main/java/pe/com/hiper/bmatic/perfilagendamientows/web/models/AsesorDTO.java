package pe.com.hiper.bmatic.perfilagendamientows.web.models;

import pe.com.hiper.bmatic.perfilagendamientows.domain.asesor.model.Asesor;

public class AsesorDTO {
    private String id;
    private String name;

    public AsesorDTO(Asesor asesor) {
        this.id = asesor.getId();
        this.name = asesor.getName();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
