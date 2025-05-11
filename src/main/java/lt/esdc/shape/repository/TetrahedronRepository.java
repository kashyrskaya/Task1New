package lt.esdc.shape.repository;

import lt.esdc.shape.action.impl.TetrahedronCalculatorImpl;
import lt.esdc.shape.entity.Point;
import lt.esdc.shape.entity.Tetrahedron;
import lt.esdc.shape.specification.TetrahedronSpecification;
import lt.esdc.shape.validator.TetrahedronValidatorImpl;
import lt.esdc.shape.warehouse.Warehouse;
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

        Warehouse warehouse = Warehouse.getInstance();
        TetrahedronCalculatorImpl calculator = new TetrahedronCalculatorImpl();

        warehouse.registerCalculator(Tetrahedron.class, calculator);
        for (Tetrahedron tetrahedron : tetrahedrons) {
            tetrahedron.addObserver(warehouse);
            tetrahedron.notifyObservers();
        }
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
        TetrahedronCalculatorImpl calculator = new TetrahedronCalculatorImpl();
        Warehouse warehouse = Warehouse.getInstance();
        try {
            warehouse.registerCalculator(Tetrahedron.class, calculator);
            tetrahedrons.add(tetrahedron);

            tetrahedron.addObserver(warehouse);
            tetrahedron.notifyObservers();
            logger.debug("Tetrahedron added successfully and registered with warehouse");
            return true;
        } catch (Exception e) {
            logger.error("Error adding Tetrahedron to repository: {}", e.getMessage());
            return false;
        }
    }

    /**
     * Updates a point of a Tetrahedron in the repository.
     *
     * @param tetrahedronId The ID of the Tetrahedron to be updated.
     * @param pointLabel    The label of the point to be updated (A, B, C, or D).
     * @param newPoint      The new Point object to replace the old one.
     * @return true if the point was successfully updated; false otherwise.
     */
    public boolean updatePoint(String tetrahedronId, char pointLabel, Point newPoint) {
        for (Tetrahedron t : tetrahedrons) {
            if (t.getId().equals(tetrahedronId)) {
                Point originalPoint;
                switch (Character.toUpperCase(pointLabel)) {
                    case 'A' -> originalPoint = t.getPointA();
                    case 'B' -> originalPoint = t.getPointB();
                    case 'C' -> originalPoint = t.getPointC();
                    case 'D' -> originalPoint = t.getPointD();
                    default -> {
                        logger.warn("Invalid point label: {}", pointLabel);
                        return false;
                    }
                }

                switch (Character.toUpperCase(pointLabel)) {
                    case 'A' -> t.setPointA(newPoint);
                    case 'B' -> t.setPointB(newPoint);
                    case 'C' -> t.setPointC(newPoint);
                    case 'D' -> t.setPointD(newPoint);
                }


                TetrahedronValidatorImpl validator = new TetrahedronValidatorImpl();
                if (validator.isValid(t)) {
                    logger.info("Updated point {} of tetrahedron with ID: {}", pointLabel, tetrahedronId);
                    return true;
                } else {
                    switch (Character.toUpperCase(pointLabel)) {
                        case 'A' -> t.setPointA(originalPoint);
                        case 'B' -> t.setPointB(originalPoint);
                        case 'C' -> t.setPointC(originalPoint);
                        case 'D' -> t.setPointD(originalPoint);
                    }
                    logger.warn("Update rolled back: new point made tetrahedron invalid");
                    return false;
                }
            }
        }

        logger.warn("Tetrahedron with ID {} not found", tetrahedronId);
        return false;
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
            tetrahedron.removeObserver(warehouse);

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
     * Sorts the Tetrahedrons in the repository using the provided comparator.
     *
     * @param comparator The comparator to use for sorting the tetrahedrons.
     */
    public void sortBy(Comparator<Tetrahedron> comparator) {
        logger.debug("Sorting tetrahedrons by custom comparator");
        tetrahedrons.sort(comparator);
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
