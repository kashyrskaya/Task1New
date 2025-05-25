package lt.esdc.shape.action;

import lt.esdc.shape.action.impl.TetrahedronCalculatorImpl;
import lt.esdc.shape.entity.AbstractShape;
import lt.esdc.shape.entity.Tetrahedron;

/**
 * Factory class for creating ShapeCalculator instances based on the type of Shape.
 * Currently, supports Tetrahedron shapes.
 */

public class CalculatorFactory {
    public static ShapeCalculator getCalculator(AbstractShape abstractShape) {
        if (abstractShape instanceof Tetrahedron) {
            return new TetrahedronCalculatorImpl();
        } else {
            throw new UnsupportedOperationException("No calculator available for shape type: " + abstractShape.getClass().getSimpleName());
        }
    }
}
