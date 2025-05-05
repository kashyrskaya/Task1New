package lt.esdc.entity.repository;

import lt.esdc.action.impl.TetrahedronCalculatorImpl;
import lt.esdc.entity.Tetrahedron;
import lt.esdc.entity.specification.TetrahedronSpecification;
import lt.esdc.exception.ShapeValidationException;
import lt.esdc.validator.TetrahedronValidatorImpl;
import lt.esdc.warehouse.Warehouse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * A repository class that manages a collection of Tetrahedron entities.
 * Provides methods to add, remove, query, and sort Tetrahedron objects based on different criteria.
 */
public class TetrahedronRepository {
    private static final Logger logger = LogManager.getLogger(TetrahedronRepository.class);
    private final List<Tetrahedron> tetrahedrons;

    /**
     * Constructs a TetrahedronRepository with the given list of Tetrahedrons.
     *
     * @param tetrahedrons The list of Tetrahedrons to be managed by this repository.
     */
    public TetrahedronRepository(List<Tetrahedron> tetrahedrons) {
        this.tetrahedrons = tetrahedrons;
    }

    /**
     * Constructs a TetrahedronRepository with an empty list of Tetrahedrons.
     * This constructor is used when no initial Tetrahedrons are provided.
     */
    public TetrahedronRepository() {
        this(new ArrayList<>());
    }

    /**
     * Adds a Tetrahedron to the repository.
     *
     * @param tetrahedron The Tetrahedron to be added.
     * @return true if the Tetrahedron was successfully added; false otherwise.
     */
    public boolean add(Tetrahedron tetrahedron) {
        logger.debug("Adding tetrahedron to repository: {}", tetrahedron);
        TetrahedronValidatorImpl validator = new TetrahedronValidatorImpl();
        TetrahedronCalculatorImpl calculator = new TetrahedronCalculatorImpl(validator);
        Warehouse warehouse = Warehouse.getInstance();
        try {
            tetrahedrons.add(tetrahedron);
            warehouse.putParameters(
                    tetrahedron.getId(),
                    calculator.computeArea(tetrahedron),
                    calculator.computePerimeter(tetrahedron),
                    calculator.computeVolume(tetrahedron)
            );
        } catch (ShapeValidationException e) {
            logger.error("Error adding Tetrahedron to repository: {}", e.getMessage());
            return false;
        }
        return true;
    }

    /**
     * Removes a Tetrahedron from the repository.
     *
     * @param tetrahedron The Tetrahedron to be removed.
     * @return true if the Tetrahedron was successfully removed; false otherwise.
     */
    public boolean remove(Tetrahedron tetrahedron) {
        logger.debug("Removing tetrahedron from repository: {}", tetrahedron);
        boolean removed = tetrahedrons.remove(tetrahedron);
        if (removed) {
            Warehouse warehouse = Warehouse.getInstance();
            warehouse.remove(tetrahedron.getId());
            logger.debug("Tetrahedron removed successfully");
        } else {
            logger.warn("Tetrahedron not found in repository");
        }
        return removed;
    }

    /**
     * Queries the repository for Tetrahedrons that satisfy the given specification.
     *
     * @param specification The specification that Tetrahedrons must satisfy.
     * @return A list of Tetrahedrons that satisfy the specification.
     */
    public List<Tetrahedron> query(TetrahedronSpecification specification) {
        logger.debug("Querying tetrahedrons from repository: {}", specification);
        return tetrahedrons.stream()
                .filter(specification::isSatisfiedBy)
                .toList();
    }

    /**
     * Sorts the Tetrahedrons in the repository by their ID.
     * Tetrahedrons will be sorted in ascending order based on their ID.
     */
    public void sortByID() { //TODO sortBy with Comparator which are classes from Interface Compare
        logger.debug("Sorting tetrahedrons by ID");
        tetrahedrons.sort(Comparator.comparing(Tetrahedron::getId));
    }

    /**
     * Sorts the Tetrahedrons in the repository by their area.
     * The area of each Tetrahedron is calculated using the provided TetrahedronCalculator.
     * In case of an error during area calculation, Tetrahedrons will be placed at the end of the list.
     *
     * @param tetrahedronCalculator The calculator used to compute the area of Tetrahedrons.
     */
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

    /**
     * Sorts the Tetrahedrons in the repository by their perimeter.
     * The perimeter of each Tetrahedron is calculated using the provided TetrahedronCalculator.
     * In case of an error during perimeter calculation, Tetrahedrons will be placed at the end of the list.
     *
     * @param tetrahedronCalculator The calculator used to compute the perimeter of Tetrahedrons.
     */
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

    /**
     * Sorts the Tetrahedrons in the repository by their volume.
     * The volume of each Tetrahedron is calculated using the provided TetrahedronCalculator.
     * In case of an error during volume calculation, Tetrahedrons will be placed at the end of the list.
     *
     * @param tetrahedronCalculator The calculator used to compute the volume of Tetrahedrons.
     */
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

    /**
     * Returns all Tetrahedrons in the repository as a new list.
     *
     * @return A list containing all Tetrahedrons in the repository.
     */
    public List<Tetrahedron> getAllTetrahedrons() {
        return new ArrayList<>(tetrahedrons);
    }
}
