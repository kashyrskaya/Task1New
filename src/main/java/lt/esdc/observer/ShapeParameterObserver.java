package lt.esdc.observer;

import lt.esdc.entity.Shape;

/**
 * Interface for observers that monitor specific parameter changes in Shape objects.
 */
public interface ShapeParameterObserver {

    /**
     * Called when a specific parameter of the observed Shape is updated.
     *
     * @param shape the Shape whose parameter was updated
     * @param parameterName the name of the updated parameter
     * @param oldParameter the old value of the parameter
     * @param newParameter the new value of the parameter
     */
    void parameterUpdate(Shape shape, String parameterName, Object oldParameter, Object newParameter);
}
