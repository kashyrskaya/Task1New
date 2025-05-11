package lt.esdc.shape.action;

import lt.esdc.shape.entity.Shape;

public interface ShapeCalculator {

    /**
     * Calculates the area of a shape
     *
     * @param shape the shape to calculate area for
     * @return the area value
     */
    double computeArea(Shape shape);

    /**
     * Calculates the perimeter of a shape
     *
     * @param shape the shape to calculate perimeter for
     * @return the perimeter value
     */
    double computePerimeter(Shape shape);

    /**
     * Calculates the volume of a shape
     *
     * @param shape the shape to calculate volume for
     * @return the volume value (for 2D shapes, returns 0)
     */
    double computeVolume(Shape shape);
}
