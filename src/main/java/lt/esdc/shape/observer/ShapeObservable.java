package lt.esdc.shape.observer;

/**
 * Interface for observable shapes that can notify observers about changes.
 * Observers can be added or removed, and they will be notified when the shape changes.
 */

public interface ShapeObservable {

    /**
     * Adds an observer to the observable object.
     *
     * @param observer the observer to add
     */
    void addObserver(ShapeObserver observer);

    /**
     * Removes an observer from the observable object.
     *
     * @param observer the observer to remove
     */
    void removeObserver(ShapeObserver observer);

    /**
     * Notifies all observers of changes in the observable object.
     */
    void notifyObservers();
}
