package lt.esdc.validator;

import lt.esdc.entity.Point;
import lt.esdc.entity.Tetrahedron;

/**
 * Implementation of the ShapeValidator interface for Tetrahedron objects.
 * Validates that the Tetrahedron is well-formed by ensuring its points are distinct
 * and that its volume is non-zero.
 */
public class TetrahedronValidatorImpl implements ShapeValidator<Tetrahedron> {

    /**
     * Validates the given Tetrahedron object.
     * Ensures that all points of the Tetrahedron are distinct and that the volume is non-zero.
     *
     * @param tetrahedron the Tetrahedron to validate
     * @return true if the Tetrahedron is valid, false otherwise
     */
    @Override
    public boolean isValid(Tetrahedron tetrahedron) {
        Point a = tetrahedron.getPointA();
        Point b = tetrahedron.getPointB();
        Point c = tetrahedron.getPointC();
        Point d = tetrahedron.getPointD();

        if (a.equals(b) || a.equals(c) || a.equals(d) ||
                b.equals(c) || b.equals(d) || c.equals(d)) {
            return false;
        }

        double ax = b.getX() - a.getX();
        double ay = b.getY() - a.getY();
        double az = b.getZ() - a.getZ();

        double bx = c.getX() - a.getX();
        double by = c.getY() - a.getY();
        double bz = c.getZ() - a.getZ();

        double cx = d.getX() - a.getX();
        double cy = d.getY() - a.getY();
        double cz = d.getZ() - a.getZ();

        double crossX = ay * bz - az * by;
        double crossY = az * bx - ax * bz;
        double crossZ = ax * by - ay * bx;

        double volume = Math.abs(crossX * cx + crossY * cy + crossZ * cz) / 6.0;

        return volume != 0;
    }
}
