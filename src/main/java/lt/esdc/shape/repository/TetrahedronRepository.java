package lt.esdc.shape.repository;

import lt.esdc.shape.entity.Tetrahedron;
import lt.esdc.shape.observer.WarehouseObserver;
import lt.esdc.shape.specification.TetrahedronSpecification;
import lt.esdc.shape.warehouse.Warehouse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;

/**
 * A repository class that manages a collection of Tetrahedron entities.
 * Provides methods to add, remove, query, and sort Tetrahedron objects based on different criteria.
 */
public class TetrahedronRepository {
    private static final Logger logger = LogManager.getLogger(TetrahedronRepository.class);
    private static TetrahedronRepository instance;
    private final List<Tetrahedron> tetrahedrons = new ArrayList<>();
    private final WarehouseObserver observer = new WarehouseObserver();

    private TetrahedronRepository() {}

    public static TetrahedronRepository getInstance() {
        if (instance == null) {
            instance = new TetrahedronRepository();
        }
        return instance;
    }

    public boolean add(Tetrahedron tetrahedron) {
        try {
            tetrahedron.addObserver(observer);
            tetrahedrons.add(tetrahedron);
            logger.debug("Tetrahedron added successfully and registered with warehouse");
            return true;
        } catch (Exception e) {
            logger.error("Error adding Tetrahedron to repository: {}", e.getMessage());
            return false;
        }
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
            tetrahedron.removeObserver(observer);

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
