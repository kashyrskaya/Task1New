package lt.esdc.observer;

import lt.esdc.entity.Shape;

public interface ShapeParameterObserver {
    void parameterUpdate(Shape shape, String parameterName, Object oldParameter, Object newParameter);
}
