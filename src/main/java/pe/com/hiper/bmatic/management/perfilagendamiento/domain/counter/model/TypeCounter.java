package pe.com.hiper.bmatic.management.perfilagendamiento.domain.counter.model;

public class TypeCounter {
    private String id;
    private String name;
    private String bookingType;

    public TypeCounter(String id, String name, String bookingType) {
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

    public static TypeCounterBuilder builder() {
        return new TypeCounterBuilder();
    }

    public static class TypeCounterBuilder {
        private String id;
        private String name;
        private String bookingType;

        public TypeCounterBuilder id(String id) {
            this.id = id;
            return this;
        }

        public TypeCounterBuilder name(String name) {
            this.name = name;
            return this;
        }

        public TypeCounterBuilder bookingType(String bookingType) {
            this.bookingType = bookingType;
            return this;
        }

        public TypeCounter build() {
            return new TypeCounter(id, name, bookingType);
        }
    }
}
