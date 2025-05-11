package lt.esdc.shape.observer;

import lt.esdc.shape.entity.Shape;

/**
 * Interface for observers in the observer design pattern.
 * Observers are notified of changes to the observable Shape objects.
 */
public interface ShapeObserver {

    /**
     * Called when the observed Shape is updated.
     *
     * @param shape the updated Shape
     */
    void update(Shape shape);
}
