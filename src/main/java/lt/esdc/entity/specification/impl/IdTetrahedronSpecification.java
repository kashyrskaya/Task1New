package lt.esdc.entity.specification.impl;

import lt.esdc.entity.Tetrahedron;
import lt.esdc.entity.specification.TetrahedronSpecification;

public class IdTetrahedronSpecification implements TetrahedronSpecification {
    private final String id;

    public IdTetrahedronSpecification(String id) {
        this.id = id;
    }

    @Override
    public boolean isSatisfiedBy(Tetrahedron tetrahedron) {

        return tetrahedron.getId().equals(id);
    }
}
