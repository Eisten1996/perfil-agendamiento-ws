package pe.com.hiper.bmatic.perfilagendamientows.domain.asesor.model;

public class Asesor {
    private String id;
    private String name;

    public Asesor(String id, String name) {
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

    public static AsesorBuilder builder() {
        return new AsesorBuilder();
    }

    public static class AsesorBuilder {
        private String id;
        private String name;

        public AsesorBuilder id(String id) {
            this.id = id;
            return this;
        }

        public AsesorBuilder name(String name) {
            this.name = name;
            return this;
        }

        public Asesor build() {
            return new Asesor(id, name);
        }
    }
}
