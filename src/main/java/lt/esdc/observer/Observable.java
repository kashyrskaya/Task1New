package lt.esdc.observer;

import java.util.List;

/**
 * Interface for observable objects in the observer design pattern.
 * Observable objects can have observers attached to them and notify those observers of changes.
 */
public interface Observable {

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
     * Notifies all attached observers of changes to the observable object.
     */
    void notifyObservers();

    /**
     * Gets the list of observers attached to the observable object.
     *
     * @return a list of observers
     */
    List<ShapeObserver> getObservers();
}
