package lt.esdc.entity.specification.impl;

import lt.esdc.entity.Tetrahedron;
import lt.esdc.entity.specification.TetrahedronSpecification;

/**
 * Specification for filtering Tetrahedron objects based on their unique identifier.
 */
public class IdTetrahedronSpecification implements TetrahedronSpecification {
    private final String id;

    /**
     * Constructs an IdTetrahedronSpecification with the specified ID.
     *
     * @param id the unique identifier to match
     */
    public IdTetrahedronSpecification(String id) {
        this.id = id;
    }

    /**
     * Checks if the given Tetrahedron's ID matches the specified ID.
     *
     * @param tetrahedron the Tetrahedron to check
     * @return true if the Tetrahedron's ID matches, false otherwise
     */
    @Override
    public boolean isSatisfiedBy(Tetrahedron tetrahedron) {
        return tetrahedron.getId().equals(id);
    }
}
