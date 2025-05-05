package lt.esdc.entity;

import lt.esdc.observer.ShapeObservable;
import lt.esdc.observer.ShapeObserver;
import lt.esdc.warehouse.Warehouse;

import java.util.List;

/**
 * Abstract base class representing a geometric shape.
 * Each shape has a unique identifier and supports observer notifications.
 */
public abstract class Shape {
    private final String id;
    protected final ShapeObservable observable;

    /**
     * Constructs a Shape with the specified unique identifier.
     *
     * @param id the unique identifier for the shape
     */
    public Shape(String id) {
        this.id = id;
        this.observable = new ShapeObservable(this);

        this.addObserver(Warehouse.getInstance());
    }

    /**
     * Gets the unique identifier of the shape.
     *
     * @return the unique identifier
     */
    public String getId() {
        return id;
    }

    /**
     * Adds an observer to the shape.
     *
     * @param observer the observer to add
     */
    public void addObserver(ShapeObserver observer) {
        observable.addObserver(observer);
    }

    /**
     * Removes an observer from the shape.
     *
     * @param observer the observer to remove
     */
    public void removeObserver(ShapeObserver observer) {
        observable.removeObserver(observer);
    }

    /**
     * Notifies all observers of changes to the shape.
     */
    public void notifyObservers() {
        observable.notifyObservers();
    }

    /**
     * Gets the list of observers attached to the shape.
     *
     * @return the list of observers
     */
    public List<ShapeObserver> getObservers() {
        return observable.getObservers();
    }

    /**
     * Checks if this shape is equal to another object.
     *
     * @param obj the object to compare
     * @return true if the objects are equal, false otherwise
     */
    @Override
    public abstract boolean equals(Object obj);

    /**
     * Computes the hash code for this shape.
     *
     * @return the hash code
     */
    @Override
    public abstract int hashCode();

    /**
     * Returns a string representation of the shape.
     *
     * @return the string representation
     */
    @Override
    public abstract String toString();
}
