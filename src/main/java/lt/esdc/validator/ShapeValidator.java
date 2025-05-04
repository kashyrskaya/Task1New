package lt.esdc.validator;

import lt.esdc.entity.Shape;

/**
 * Functional interface for validating Shape objects.
 * Implementations of this interface define the criteria for determining
 * whether a given Shape is valid.
 *
 * @param <T> the type of Shape to validate
 */
@FunctionalInterface
public interface ShapeValidator<T extends Shape> {

    /**
     * Validates the given Shape object.
     *
     * @param shape the Shape to validate
     * @return true if the Shape is valid, false otherwise
     */
    boolean isValid(T shape);
}
