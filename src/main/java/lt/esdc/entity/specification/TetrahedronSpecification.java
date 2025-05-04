package lt.esdc.entity.specification;

import lt.esdc.entity.Tetrahedron;

public interface TetrahedronSpecification {
    boolean isSatisfiedBy(Tetrahedron tetrahedron);
}
