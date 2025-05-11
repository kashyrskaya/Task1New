package lt.esdc.shape.comparator;

import lt.esdc.shape.action.impl.TetrahedronCalculatorImpl;
import lt.esdc.shape.entity.Tetrahedron;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Comparator;

public class TetrahedronByPerimeterComparator implements Comparator<Tetrahedron> {
    private final TetrahedronCalculatorImpl calculator;
    private static final Logger logger = LogManager.getLogger(TetrahedronByPerimeterComparator.class);

    /**
     * Constructs a new TetrahedronByPerimeterComparator with a new calculator instance.
     */
    public TetrahedronByPerimeterComparator() {
        this.calculator = new TetrahedronCalculatorImpl();
    }

    /**
     * Compares two Tetrahedron objects based on their perimeter.
     *
     * @param o1 The first Tetrahedron to compare.
     * @param o2 The second Tetrahedron to compare.
     * @return A negative integer, zero, or a positive integer as the first Tetrahedron's perimeter is less than,
     * equal to, or greater than the second Tetrahedron's perimeter.
     */
    @Override
    public int compare(Tetrahedron o1, Tetrahedron o2) {
        logger.debug("Comparing Tetrahedrons: {} and {}", o1, o2);
        try {
            double perimeter1 = calculator.computePerimeter(o1);
            double perimeter2 = calculator.computePerimeter(o2);
            return Double.compare(perimeter1, perimeter2);
        } catch (Exception e) {
            logger.error("Error calculating perimeter for Tetrahedrons: {} and {}", o1, o2, e);
            return 0;
        }
    }
}
