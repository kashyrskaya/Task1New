package lt.esdc.shape.validator;

import lt.esdc.shape.entity.Point;
import lt.esdc.shape.entity.Tetrahedron;
import lt.esdc.shape.util.action.VectorUtil;
import lt.esdc.shape.util.entity.Vector3D;

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

        Vector3D ab = new Vector3D(b.getX() - a.getX(), b.getY() - a.getY(), b.getZ() - a.getZ());
        Vector3D ac = new Vector3D(c.getX() - a.getX(), c.getY() - a.getY(), c.getZ() - a.getZ());
        Vector3D ad = new Vector3D(d.getX() - a.getX(), d.getY() - a.getY(), d.getZ() - a.getZ());

        Vector3D cross = VectorUtil.cross(ac, ad);
        double volume = Math.abs(VectorUtil.dot(ab, cross)) / 6.0;

        return volume != 0;
    }
}
