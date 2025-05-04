package lt.esdc.entity.specification.impl;

import lt.esdc.action.impl.TetrahedronCalculatorImpl;
import lt.esdc.entity.Tetrahedron;
import lt.esdc.entity.specification.TetrahedronSpecification;

public class AreaRangeTetrahedronSpecification implements TetrahedronSpecification {
    private final double minArea;
    private final double maxArea;
    private final TetrahedronCalculatorImpl tetrahedronCalculator;

    public AreaRangeTetrahedronSpecification(TetrahedronCalculatorImpl tetrahedronCalculator, double maxArea, double minArea) {
        this.tetrahedronCalculator = tetrahedronCalculator;
        this.maxArea = maxArea;
        this.minArea = minArea;
    }

    @Override
    public boolean isSatisfiedBy(Tetrahedron tetrahedron) {
        try {
            double area = tetrahedronCalculator.computeArea(tetrahedron);
            return area >= minArea && area <= maxArea;
        } catch (Exception e) { //TODO: same as perimeter
            return false;
        }
    }
}
