package pe.com.hiper.bmatic.management.perfilagendamiento.domain.counter.model;

import java.util.List;

import pe.com.hiper.bmatic.management.perfilagendamiento.domain.service.model.Service;

public class Counter {
    private String id;
    private String name;
    private List<Service> services;

    public Counter(String id, String name, List<Service> services) {
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

    public static CounterBuilder builder() {
        return new CounterBuilder();
    }

    public static class CounterBuilder {
        private String id;
        private String name;
        private List<Service> services;

        public CounterBuilder id(String id) {
            this.id = id;
            return this;
        }

        public CounterBuilder name(String name) {
            this.name = name;
            return this;
        }

        public CounterBuilder name(List<Service> services) {
            this.services = services;
            return this;
        }

        public Counter build() {
            return new Counter(id, name, services);
        }
    }
}
