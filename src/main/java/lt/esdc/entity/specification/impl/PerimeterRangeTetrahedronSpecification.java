package lt.esdc.entity.specification.impl;

import lt.esdc.action.impl.TetrahedronCalculatorImpl;
import lt.esdc.entity.Tetrahedron;
import lt.esdc.entity.specification.TetrahedronSpecification;

public class PerimeterRangeTetrahedronSpecification implements TetrahedronSpecification {
    private final double minPerimeter;
    private final double maxPerimeter;
    private final TetrahedronCalculatorImpl tetrahedronCalculator;

    public PerimeterRangeTetrahedronSpecification(double minPerimeter, double maxPerimeter, TetrahedronCalculatorImpl tetrahedronCalculator) {
        this.minPerimeter = minPerimeter;
        this.maxPerimeter = maxPerimeter;
        this.tetrahedronCalculator = tetrahedronCalculator;
    }

    @Override
    public boolean isSatisfiedBy(Tetrahedron tetrahedron) {
        try {
            double perimeter = tetrahedronCalculator.computePerimeter(tetrahedron);
            return perimeter >= minPerimeter && perimeter <= maxPerimeter;
        } catch (Exception e) { //TODO: introduce custom exception
            return false;
        }
    }
}
