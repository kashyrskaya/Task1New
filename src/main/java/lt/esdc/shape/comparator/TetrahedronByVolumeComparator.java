package lt.esdc.shape.comparator;

import lt.esdc.shape.action.impl.TetrahedronCalculatorImpl;
import lt.esdc.shape.entity.Tetrahedron;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Comparator;

public class TetrahedronByVolumeComparator implements Comparator<Tetrahedron> {
    private final TetrahedronCalculatorImpl calculator;
    private static final Logger logger = LogManager.getLogger(TetrahedronByVolumeComparator.class);

    /**
     * Constructs a new TetrahedronByVolumeComparator with a new calculator instance.
     */
    public TetrahedronByVolumeComparator() {
        this.calculator = new TetrahedronCalculatorImpl();
    }

    /**
     * Compares two Tetrahedron objects based on their volume.
     *
     * @param o1 The first Tetrahedron to compare.
     * @param o2 The second Tetrahedron to compare.
     * @return A negative integer, zero, or a positive integer as the first Tetrahedron's volume is less than,
     * equal to, or greater than the second Tetrahedron's volume.
     */
    @Override
    public int compare(Tetrahedron o1, Tetrahedron o2) {
        logger.debug("Comparing Tetrahedrons: {} and {}", o1, o2);
        try {
            double volume1 = calculator.computeVolume(o1);
            double volume2 = calculator.computeVolume(o2);
            return Double.compare(volume1, volume2);
        } catch (Exception e) {
            logger.error("Error calculating volume for Tetrahedrons: {} and {}", o1, o2, e);
            return 0;
        }
    }
}
