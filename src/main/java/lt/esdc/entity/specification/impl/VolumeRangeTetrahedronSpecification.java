package lt.esdc.entity.specification.impl;

import lt.esdc.action.impl.TetrahedronCalculatorImpl;
import lt.esdc.entity.Tetrahedron;
import lt.esdc.entity.specification.TetrahedronSpecification;

public class VolumeRangeTetrahedronSpecification implements TetrahedronSpecification {
    private final double minVolume;
    private final double maxVolume;
    private final TetrahedronCalculatorImpl tetrahedronCalculator;

    public VolumeRangeTetrahedronSpecification(double minVolume, double maxVolume, TetrahedronCalculatorImpl tetrahedronCalculator) {
        this.minVolume = minVolume;
        this.maxVolume = maxVolume;
        this.tetrahedronCalculator = tetrahedronCalculator;
    }

    @Override
    public boolean isSatisfiedBy(Tetrahedron tetrahedron) {
        try {
            double volume = tetrahedronCalculator.computeVolume(tetrahedron);
            return volume >= minVolume && volume <= maxVolume;
        } catch (Exception e) { //TODO: exception
            return false;
        }
    }
}
