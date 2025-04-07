package lt.esdc.entity;

public abstract class Shape {
    private final String id;

    public Shape(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    @Override
    public abstract boolean equals(Object obj);

    @Override
    public abstract int hashCode();

    @Override
    public abstract String toString();
}
