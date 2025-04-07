package lt.esdc.factory;

import lt.esdc.entity.Shape;
import lt.esdc.exception.ShapeValidationException;

public abstract class ShapeFactory {
    public abstract Shape createShape(String[] parameters) throws ShapeValidationException;
}
