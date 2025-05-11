package lt.esdc.shape.factory;

import lt.esdc.shape.entity.Shape;
import lt.esdc.shape.exception.ShapeValidationException;

/**
 * Abstract factory class for creating Shape objects.
 * Subclasses of this factory are responsible for implementing the creation logic
 * for specific types of shapes.
 */
public abstract class ShapeFactory {

    /**
     * Creates a Shape object from the specified parameters.
     *
     * @param parameters an array of strings representing the shape's properties
     * @return the created Shape object
     * @throws ShapeValidationException if the parameters are invalid or insufficient
     */
    public abstract Shape createShape(String[] parameters) throws ShapeValidationException;
}
