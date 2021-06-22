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

    public static Branch.BranchBuilder builder() {
        return new Branch.BranchBuilder();
    }

    public static class BranchBuilder {
        private String codBranch;
        private String agName;

        public BranchBuilder codBranch(String codBranch) {
            this.codBranch = codBranch;
            return this;
        }

        public BranchBuilder agName(String agName) {
            this.agName = agName;
            return this;
        }

        public Branch build() {
            return new Branch(codBranch, agName);
        }
    }
}
