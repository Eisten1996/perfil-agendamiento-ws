package pe.com.hiper.bmatic.management.perfilagendamiento.domain.asesor.model;

import java.util.ArrayList;
import java.util.List;

import pe.com.hiper.bmatic.management.perfilagendamiento.domain.service.model.Service;

public class Asesor {
    private String id;
    private String name;
    private List<Service> services;
    
    public Asesor(String id, String name, List<Service> services) {
        this.id = id;
        this.name = name;
        this.services = services;
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

    public List<Service> getServices() {
        return services;
    }

    public void setServices(List<Service> services) {
        this.services = services;
    }

    public static AsesorBuilder builder() {
        return new AsesorBuilder();
    }

    public static class AsesorBuilder {
        private String id;
        private String name;
        private List<Service> services = new ArrayList<Service>();

        public AsesorBuilder id(String id) {
            this.id = id;
            return this;
        }

        public AsesorBuilder name(String name) {
            this.name = name;
            return this;
        }

        public AsesorBuilder services(List<Service> services) {
            this.services = services;
            return this;
        }

        public Asesor build() {
            return new Asesor(id, name, services);
        }
    }
}
