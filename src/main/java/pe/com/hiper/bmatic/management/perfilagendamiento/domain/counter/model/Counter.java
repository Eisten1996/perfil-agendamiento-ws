package pe.com.hiper.bmatic.management.perfilagendamiento.domain.counter.model;

public class Counter {
    private String id;
    private String name;

    public Counter(String id, String name) {
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

    public static CounterBuilder builder() {
        return new CounterBuilder();
    }

    public static class CounterBuilder {
        private String id;
        private String name;

        public CounterBuilder id(String id) {
            this.id = id;
            return this;
        }

        public CounterBuilder name(String name) {
            this.name = name;
            return this;
        }

        public Counter build() {
            return new Counter(id, name);
        }
    }
}
