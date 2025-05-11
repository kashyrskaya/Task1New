package lt.esdc.shape.specification.impl;

import lt.esdc.shape.action.ShapeCalculator;
import lt.esdc.shape.entity.Tetrahedron;
import lt.esdc.shape.specification.TetrahedronSpecification;

/**
 * Specification for filtering Tetrahedron objects based on their perimeter.
 * The Tetrahedron must have a perimeter within the specified range.
 */
public class PerimeterRangeTetrahedronSpecification implements TetrahedronSpecification {
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
        double perimeter = shapeCalculator.computePerimeter(tetrahedron);
        return perimeter >= minPerimeter && perimeter <= maxPerimeter;
    }
}
