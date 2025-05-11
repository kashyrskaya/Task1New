package lt.esdc.shape.comparator;

import lt.esdc.shape.action.impl.TetrahedronCalculatorImpl;
import lt.esdc.shape.entity.Tetrahedron;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Comparator;

public class TetrahedronByAreaComparator implements Comparator<Tetrahedron> {
    private static final Logger logger = LogManager.getLogger(TetrahedronByAreaComparator.class);
    private final TetrahedronCalculatorImpl calculator;

    /**
     * Constructs a new TetrahedronByAreaComparator with a new calculator instance.
     */
    public TetrahedronByAreaComparator() {
        this.calculator = new TetrahedronCalculatorImpl();
    }

    /**
     * Compares two Tetrahedron objects based on their surface area.
     *
     * @param o1 The first Tetrahedron to compare.
     * @param o2 The second Tetrahedron to compare.
     * @return A negative integer, zero, or a positive integer as the first Tetrahedron's area is less than,
     * equal to, or greater than the second Tetrahedron's area.
     */
    @Override
    public int compare(Tetrahedron o1, Tetrahedron o2) {
        logger.debug("Comparing Tetrahedrons: {} and {}", o1, o2);
        try {
            double area1 = calculator.computeArea(o1);
            double area2 = calculator.computeArea(o2);
            return Double.compare(area1, area2);
        } catch (Exception e) {
            logger.error("Error calculating area for Tetrahedrons: {} and {}", o1, o2, e);
            return 0;
        }
    }
}

