package pe.com.hiper.bmatic.management.perfilagendamiento.domain.service.model;

public class Service {
    private String id;
    private String name;

    public Service(String id, String name) {
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

    public static ServiceBuilder builder() {
        return new ServiceBuilder();
    }

    public static class ServiceBuilder {
        private String id;
        private String name;

        public ServiceBuilder id(String id) {
            this.id = id;
            return this;
        }

        public ServiceBuilder name(String name) {
            this.name = name;
            return this;
        }

        public Service build() {
            return new Service(id, name);
        }
    }
}
