package lt.esdc.shape.factory;

import lt.esdc.shape.entity.AbstractShape;
import lt.esdc.shape.exception.ShapeValidationException;

/**
 * Abstract factory class for creating Shape instances.
 * Subclasses should implement the createShape method to instantiate specific shapes.
 */

public abstract class AbstractShapeFactory {

    public abstract AbstractShape createShape(String[] parameters) throws ShapeValidationException;
}
