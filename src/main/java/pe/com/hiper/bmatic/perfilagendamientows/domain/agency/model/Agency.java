package pe.com.hiper.bmatic.perfilagendamientows.domain.agency.model;

import pe.com.hiper.bmatic.perfilagendamientows.domain.scheduling.model.Scheduling;

public class Agency {
    private String codAgency;
    private String agName;

    public Agency(String codAgency, String agName) {
        this.codAgency = codAgency;
        this.agName = agName;
    }

    public String getCodAgency() {
        return codAgency;
    }

    public void setCodAgency(String codAgency) {
        this.codAgency = codAgency;
    }

    public String getAgName() {
        return agName;
    }

    public void setAgName(String agName) {
        this.agName = agName;
    }

    public static Agency.AgencyBuilder builder() {
        return new Agency.AgencyBuilder();
    }

    public static class AgencyBuilder {
        private String codAgency;
        private String agName;

        public AgencyBuilder codAgency(String codAgency) {
            this.codAgency = codAgency;
            return this;
        }

        public AgencyBuilder agName(String agName) {
            this.agName = agName;
            return this;
        }

        public Agency build() {
            return new Agency(codAgency, agName);
        }
    }
}
