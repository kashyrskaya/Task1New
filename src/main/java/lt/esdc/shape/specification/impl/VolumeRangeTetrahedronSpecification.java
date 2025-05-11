package lt.esdc.shape.specification.impl;

import lt.esdc.shape.action.ShapeCalculator;
import lt.esdc.shape.entity.Tetrahedron;
import lt.esdc.shape.specification.TetrahedronSpecification;

/**
 * Specification for filtering Tetrahedron objects based on their volume.
 * The Tetrahedron must have a volume within the specified range.
 */
public class VolumeRangeTetrahedronSpecification implements TetrahedronSpecification {
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
        double volume = shapeCalculator.computeVolume(tetrahedron);
        return volume >= minVolume && volume <= maxVolume;

    }
}
