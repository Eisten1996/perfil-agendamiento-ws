package pe.com.hiper.bmatic.perfilagendamientows.domain.agency.model;

public class Branch {
    private final String codProfileScheduler;
    private String nameBranch;
    private final String codBranch;
    private int maxDayScheduler;
    private int minDayScheduler;

    public Branch(String codProfileScheduler, String nameBranch, String codBranch, int maxDayScheduler, int minDayScheduler) {
        this.codProfileScheduler = codProfileScheduler;
        this.nameBranch = nameBranch;
        this.codBranch = codBranch;
        this.maxDayScheduler = maxDayScheduler;
        this.minDayScheduler = minDayScheduler;
    }

    public String getId() {
        return codProfileScheduler;
    }

    public String getNameBranch() {
        return nameBranch;
    }

    public void setNameBranch(String nameBranch) {
        this.nameBranch = nameBranch;
    }

    public int getMaxDayScheduler() {
        return maxDayScheduler;
    }

    public void setMaxDayScheduler(int maxDayScheduler) {
        this.maxDayScheduler = maxDayScheduler;
    }

    public int getMinDayScheduler() {
        return minDayScheduler;
    }

    public void setMinDayScheduler(int minDayScheduler) {
        this.minDayScheduler = minDayScheduler;
    }

    public static BranchBuilder builder() {
        return new BranchBuilder();
    }

    public static class BranchBuilder {
        private String codProfileScheduler;
        private String nameBranch;
        private String codBranch;
        private int maxDayScheduler;
        private int minDayScheduler;

        public BranchBuilder codProfileScheduler(String codProfileScheduler) {
            this.codProfileScheduler = codProfileScheduler;
            return this;
        }

        public BranchBuilder nameBranch(String nameBranch) {
            this.nameBranch = nameBranch;
            return this;
        }

        public BranchBuilder codBranch(String codBranch) {
            this.codBranch = codBranch;
            return this;
        }

        public BranchBuilder maxDayScheduler(int maxDayScheduler) {
            this.maxDayScheduler = maxDayScheduler;
            return this;
        }

        public BranchBuilder minDayScheduler(int minDayScheduler) {
            this.minDayScheduler = minDayScheduler;
            return this;
        }

        public Branch build() {
            return new Branch(codProfileScheduler, nameBranch, codBranch, maxDayScheduler, minDayScheduler);
        }
    }
}
