package lt.esdc.action;

import lt.esdc.entity.Shape;
import lt.esdc.exception.ShapeValidationException;

public interface ShapeCalculator {

    /**
     * Calculates the area of a shape
     * @param shape the shape to calculate area for
     * @return the area value
     */
    double computeArea(Shape shape) throws ShapeValidationException;

    /**
     * Calculates the perimeter of a shape
     * @param shape the shape to calculate perimeter for
     * @return the perimeter value
     */
    double computePerimeter(Shape shape) throws ShapeValidationException;

    /**
     * Calculates the volume of a shape
     * @param shape the shape to calculate volume for
     * @return the volume value (for 2D shapes, returns 0)
     */
    double computeVolume(Shape shape) throws ShapeValidationException;
}
