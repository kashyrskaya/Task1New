package lt.esdc.entity.specification.impl;

import lt.esdc.action.ShapeCalculator;
import lt.esdc.entity.Tetrahedron;
import lt.esdc.entity.specification.TetrahedronSpecification;
import lt.esdc.exception.ShapeValidationException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Specification for filtering Tetrahedron objects based on their surface area.
 * The Tetrahedron must have an area within the specified range.
 */
public class AreaRangeTetrahedronSpecification implements TetrahedronSpecification {
    private static final Logger logger = LogManager.getLogger(AreaRangeTetrahedronSpecification.class);
    private final double minArea;
    private final double maxArea;
    private final ShapeCalculator shapeCalculator ;

    /**
     * Constructs an AreaRangeTetrahedronSpecification with the specified area range and ShapeCalculator.
     *
     * @param shapeCalculator the calculator used to compute the Tetrahedron's area
     * @param maxArea the maximum allowable area
     * @param minArea the minimum allowable area
     */
    public AreaRangeTetrahedronSpecification(ShapeCalculator shapeCalculator, double maxArea, double minArea) {
        this.shapeCalculator = shapeCalculator;
        this.minArea = minArea;
        this.maxArea = maxArea;
    }

    /**
     * Checks if the given Tetrahedron's area is within the specified range.
     *
     * @param tetrahedron the Tetrahedron to check
     * @return true if the Tetrahedron's area is within the range, false otherwise
     */
    @Override
    public boolean isSatisfiedBy(Tetrahedron tetrahedron) {
        try {
            double area = shapeCalculator.computeArea(tetrahedron);
            return area >= minArea && area <= maxArea;
        } catch (ShapeValidationException e) {
            logger.debug("Invalid shape is passed to the method {}", e.getMessage());
            return false;
        }
    }
}
