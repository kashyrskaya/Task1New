package lt.esdc.validator;

import lt.esdc.entity.Point;
import lt.esdc.entity.Tetrahedron;

public class TetrahedronValidatorImpl implements ShapeValidator<Tetrahedron> {

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
