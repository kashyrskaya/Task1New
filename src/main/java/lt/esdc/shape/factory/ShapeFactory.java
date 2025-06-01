package lt.esdc.shape.factory;

import lt.esdc.shape.entity.AbstractShape;
import lt.esdc.shape.exception.ShapeValidationException;

import java.util.List;

/**
 * Abstract factory class for creating Shape instances.
 * Subclasses should implement the createShape method to instantiate specific shapes.
 */

public interface ShapeFactory {

    AbstractShape createShape(List<Double> parameters) throws ShapeValidationException;
}
