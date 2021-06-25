package pe.com.hiper.bmatic.perfilagendamientows.domain.counter.model;

public class Counter {
    private String id;
    private String name;
    private String bookingType;

    public Counter(String id, String name, String bookingType) {
        this.id = id;
        this.name = name;
        this.bookingType = bookingType;
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

    public String getBookingType() {
        return bookingType;
    }

    public void setBookingType(String bookingType) {
        this.bookingType = bookingType;
    }

    public static Counter.CounterBuilder builder() {
        return new Counter.CounterBuilder();
    }

    public static class CounterBuilder {
        private String id;
        private String name;
        private String bookingType;

        public CounterBuilder id(String id) {
            this.id = id;
            return this;
        }

        public CounterBuilder name(String name) {
            this.name = name;
            return this;
        }

        public CounterBuilder bookingType(String bookingType) {
            this.bookingType = bookingType;
            return this;
        }

        public Counter build() {
            return new Counter(id, name, bookingType);
        }
    }
}
