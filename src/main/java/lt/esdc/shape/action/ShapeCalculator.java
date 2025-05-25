package lt.esdc.shape.action;

import lt.esdc.shape.entity.AbstractShape;

/**
 * Interface for calculating properties of shapes.
 * Provides methods to compute area, perimeter, and volume of a shape.
 * Implementations should handle specific shape types.
 */

public interface ShapeCalculator {

    /**
     * Calculates the area of a shape
     *
     * @param abstractShape the shape to calculate area for
     * @return the area value
     */
    double computeArea(AbstractShape abstractShape);

    /**
     * Calculates the perimeter of a shape
     *
     * @param abstractShape the shape to calculate perimeter for
     * @return the perimeter value
     */
    double computePerimeter(AbstractShape abstractShape);

    /**
     * Calculates the volume of a shape
     *
     * @param abstractShape the shape to calculate volume for
     * @return the volume value (for 2D shapes, returns 0)
     */
    double computeVolume(AbstractShape abstractShape);
}
