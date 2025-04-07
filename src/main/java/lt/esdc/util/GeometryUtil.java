package lt.esdc.util;

import lt.esdc.entity.Point;

public class GeometryUtil {

    private static final double EPSILON = 1e-10;

    public static double computeDistance(Point p1, Point p2) {
        double dx = p2.getX() - p1.getX();
        double dy = p2.getY() - p1.getY();
        double dz = p2.getZ() - p1.getZ();

        return Math.sqrt(dx * dx + dy * dy + dz * dz);
    }

    public static double computeTriangleArea(Point a, Point b, Point c) {

        double ab = computeDistance(a, b);
        double ac = computeDistance(a, c);
        double bc = computeDistance(b, c);

        double s = (ab + ac + bc) / 2;
        return Math.sqrt(Math.max(0, s * (s - ab) * (s - ac) * (s - bc)));
    }
}
