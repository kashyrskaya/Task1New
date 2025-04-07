package lt.esdc.validator;

import lt.esdc.entity.Shape;

@FunctionalInterface
public interface ShapeValidator<T extends Shape> {

    boolean isValid(T shape);

}
