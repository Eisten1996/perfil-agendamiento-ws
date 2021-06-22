package pe.com.hiper.bmatic.perfilagendamientows.domain.branch.model;

public class Branch {
    private String id;
    private String name;

    public Branch(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static Branch.AgencyBuilder builder() {
        return new Branch.AgencyBuilder();
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

        public Branch build() {
            return new Branch(codAgency, agName);
        }
    }
}
