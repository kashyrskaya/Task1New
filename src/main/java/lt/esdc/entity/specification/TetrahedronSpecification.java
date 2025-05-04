package lt.esdc.entity.specification;

import lt.esdc.entity.Tetrahedron;

/**
 * Interface for defining specifications for Tetrahedron objects.
 * A specification is a condition or rule that a Tetrahedron must satisfy.
 */
public interface TetrahedronSpecification {
    /**
     * Checks if the given Tetrahedron satisfies the specification.
     *
     * @param tetrahedron the Tetrahedron to check
     * @return true if the Tetrahedron satisfies the specification, false otherwise
     */
    boolean isSatisfiedBy(Tetrahedron tetrahedron);
}
