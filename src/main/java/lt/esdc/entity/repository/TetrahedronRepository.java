package lt.esdc.entity.repository;

import lt.esdc.action.impl.TetrahedronCalculatorImpl;
import lt.esdc.entity.Tetrahedron;
import lt.esdc.entity.specification.TetrahedronSpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class TetrahedronRepository {
    private static final Logger logger = LogManager.getLogger(TetrahedronRepository.class);
    private final List<Tetrahedron> tetrahedrons = new ArrayList<>();

    public boolean add(Tetrahedron tetrahedron) {
        logger.debug("Adding tetrahedron to repository: {}", tetrahedron);
        return tetrahedrons.add(tetrahedron);
    }

    public boolean remove(Tetrahedron tetrahedron) {
        logger.debug("Removing tetrahedron from repository: {}", tetrahedron);
        return tetrahedrons.remove(tetrahedron);
    }

    public List<Tetrahedron> query(TetrahedronSpecification specification) {
        logger.debug("Querying tetrahedrons from repository: {}", specification);
        return tetrahedrons.stream()
                .filter(specification::isSatisfiedBy)
                .toList();
    }

    public void sortByID() {
        logger.debug("Sorting tetrahedrons by ID");
        tetrahedrons.sort(Comparator.comparing(Tetrahedron::getId));
    }

    public void sortByArea(TetrahedronCalculatorImpl tetrahedronCalculator) {
        logger.debug("Sorting tetrahedrons by area");
        tetrahedrons.sort(Comparator.comparingDouble(tetrahedron -> {
            try {
                return tetrahedronCalculator.computeArea(tetrahedron);
            } catch (Exception e) {
                logger.error("Error calculating Area for sorting: {}", e.getMessage());
                return Double.MAX_VALUE;
            }
        }));
    }

    public void sortByPerimeter(TetrahedronCalculatorImpl tetrahedronCalculator) {
        logger.debug("Sorting tetrahedrons by perimeter");
        tetrahedrons.sort(Comparator.comparingDouble(tetrahedron -> {
            try {
                return tetrahedronCalculator.computePerimeter(tetrahedron);
            } catch (Exception e) {
                logger.error("Error calculating Perimeter for sorting: {}", e.getMessage());
                return Double.MAX_VALUE;
            }
        }));
    }

    public void sortByVolume(TetrahedronCalculatorImpl tetrahedronCalculator) {
        logger.debug("Sorting tetrahedrons by volume");
        tetrahedrons.sort(Comparator.comparingDouble(tetrahedron -> {
            try {
                return tetrahedronCalculator.computeVolume(tetrahedron);
            } catch (Exception e) {
                logger.error("Error calculating Volume for sorting: {}", e.getMessage());
                return Double.MAX_VALUE;
            }
        }));
    }

    public List<Tetrahedron> getAllTetrahedrons() {
        return new ArrayList<>(tetrahedrons);
    }
}
