package lt.esdc.shape.comparator;

import lt.esdc.shape.entity.Tetrahedron;

import java.util.Comparator;

public class TetrahedronByIdComparator implements Comparator<Tetrahedron> {
    /**
     * Compares two Tetrahedron objects based on their unique identifiers.
     *
     * @param o1 the first Tetrahedron to compare
     * @param o2 the second Tetrahedron to compare
     * @return a negative integer, zero, or a positive integer as the first Tetrahedron's ID is less than,
     * equal to, or greater than the second Tetrahedron's ID
     */
    @Override
    public int compare(Tetrahedron o1, Tetrahedron o2) {
        return Comparator.comparing(Tetrahedron::getId).compare(o1, o2);
    }
}
