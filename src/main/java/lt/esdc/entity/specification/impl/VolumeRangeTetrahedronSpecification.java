package lt.esdc.entity.specification.impl;

import lt.esdc.action.ShapeCalculator;
import lt.esdc.entity.Tetrahedron;
import lt.esdc.entity.specification.TetrahedronSpecification;
import lt.esdc.exception.ShapeValidationException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Specification for filtering Tetrahedron objects based on their volume.
 * The Tetrahedron must have a volume within the specified range.
 */
public class VolumeRangeTetrahedronSpecification implements TetrahedronSpecification {
    private static final Logger logger = LogManager.getLogger(VolumeRangeTetrahedronSpecification.class);
    private final double minVolume;
    private final double maxVolume;
    private final ShapeCalculator shapeCalculator;

    /**
     * Constructs a VolumeRangeTetrahedronSpecification with the specified volume range and ShapeCalculator.
     *
     * @param shapeCalculator the calculator used to compute the Tetrahedron's volume
     * @param minVolume the minimum allowable volume
     * @param maxVolume the maximum allowable volume
     */
    public VolumeRangeTetrahedronSpecification(ShapeCalculator shapeCalculator, double minVolume, double maxVolume) {
        this.shapeCalculator = shapeCalculator;
        this.minVolume = minVolume;
        this.maxVolume = maxVolume;
    }

    /**
     * Checks if the given Tetrahedron's volume is within the specified range.
     *
     * @param tetrahedron the Tetrahedron to check
     * @return true if the Tetrahedron's volume is within the range, false otherwise
     */
    @Override
    public boolean isSatisfiedBy(Tetrahedron tetrahedron) {
        try {
            double volume = shapeCalculator.computeVolume(tetrahedron);
            return volume >= minVolume && volume <= maxVolume;
        } catch (ShapeValidationException e) {
            logger.debug("Invalid shape is passed {}", e.getMessage());
            return false;
        }
    }
}
