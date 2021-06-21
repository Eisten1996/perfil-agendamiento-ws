package pe.com.hiper.bmatic.perfilagendamientows.web.models;

import pe.com.hiper.bmatic.perfilagendamientows.domain.agency.model.Agency;

public class AgencyDTO {
    private String codAgency;
    private String agName;

    public AgencyDTO(Agency agency) {
        this.codAgency = agency.getCodAgency();
        this.agName = agency.getAgName();
    }

    public String getCodAgency() {
        return codAgency;
    }

    public String getAgName() {
        return agName;
    }
}
