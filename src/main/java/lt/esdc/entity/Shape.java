package lt.esdc.entity;

import lt.esdc.observer.ShapeObservable;
import lt.esdc.observer.ShapeObserver;
import lt.esdc.warehouse.Warehouse;

import java.util.List;

public abstract class Shape {
    private String id;
    protected final ShapeObservable observable;

    public Shape(String id) {
        this.id = id;
        this.observable = new ShapeObservable(this);

        this.addObserver(Warehouse.getInstance());
    }

    public String getId() {

        return id;
    }

    public void setId(String id) {
        String oldId = this.id;
        this.id = id;
        notifyObservers();
    }

    public void addObserver(ShapeObserver observer) {
        observable.addObserver(observer);
    }

    public void removeObserver(ShapeObserver observer) {
        observable.removeObserver(observer);
    }

    public void notifyObservers() {
        observable.notifyObservers();
    }

    public List<ShapeObserver> getObservers() {
        return observable.getObservers();
    }

    @Override
    public abstract boolean equals(Object obj);

    @Override
    public abstract int hashCode();

    @Override
    public abstract String toString();
}
