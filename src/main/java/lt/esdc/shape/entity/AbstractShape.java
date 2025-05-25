package lt.esdc.shape.entity;

import lt.esdc.shape.observer.ShapeObserver;

import java.util.ArrayList;
import java.util.List;

/**
 * Abstract base class representing a geometric shape.
 * Each shape has a unique identifier and supports observer notifications.
 */
public abstract class AbstractShape {
    private final String id;
    private final List<ShapeObserver> observers = new ArrayList<>();

    /**
     * Constructs a Shape with the specified unique identifier.
     *
     * @param id the unique identifier for the shape
     */
    public AbstractShape(String id) {
        this.id = id;
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
        observers.add(observer);
    }

    /**
     * Removes an observer from the shape.
     *
     * @param observer the observer to remove
     */
    public void removeObserver(ShapeObserver observer) {
        observers.remove(observer);
    }

    /**
     * Notifies all observers of changes to the shape.
     */
    public void notifyObservers() {
        for (ShapeObserver observer : observers) {
            observer.update(this);
        }
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
