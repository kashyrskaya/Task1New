package lt.esdc.entity.specification.impl;

import lt.esdc.action.ShapeCalculator;
import lt.esdc.entity.Tetrahedron;
import lt.esdc.entity.specification.TetrahedronSpecification;
import lt.esdc.exception.ShapeValidationException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Specification for filtering Tetrahedron objects based on their perimeter.
 * The Tetrahedron must have a perimeter within the specified range.
 */
public class PerimeterRangeTetrahedronSpecification implements TetrahedronSpecification {
    private static final Logger logger = LogManager.getLogger(PerimeterRangeTetrahedronSpecification.class);
    private final double minPerimeter;
    private final double maxPerimeter;
    private final ShapeCalculator shapeCalculator;

    /**
     * Constructs a PerimeterRangeTetrahedronSpecification with the specified perimeter range and ShapeCalculator.
     *
     * @param shapeCalculator the calculator used to compute the Tetrahedron's perimeter
     * @param minPerimeter the minimum allowable perimeter
     * @param maxPerimeter the maximum allowable perimeter
     */
    public PerimeterRangeTetrahedronSpecification(ShapeCalculator shapeCalculator, double minPerimeter, double maxPerimeter) {
        this.shapeCalculator = shapeCalculator;
        this.minPerimeter = minPerimeter;
        this.maxPerimeter = maxPerimeter;
    }

    /**
     * Checks if the given Tetrahedron's perimeter is within the specified range.
     *
     * @param tetrahedron the Tetrahedron to check
     * @return true if the Tetrahedron's perimeter is within the range, false otherwise
     */
    @Override
    public boolean isSatisfiedBy(Tetrahedron tetrahedron) {
        try {
            double perimeter = shapeCalculator.computePerimeter(tetrahedron);
            return perimeter >= minPerimeter && perimeter <= maxPerimeter;
        } catch (ShapeValidationException e) {
            logger.debug("Invalid shape is passed to the method {}", e.getMessage());
            return false;
        }
    }
}
