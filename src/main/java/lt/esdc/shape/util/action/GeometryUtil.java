package lt.esdc.shape.util.action;

import lt.esdc.shape.entity.Point;
import lt.esdc.shape.util.entity.Vector3D;

/**
 * Utility class for performing geometric calculations.
 * Provides methods to compute distances and areas in 3D space.
 */
public class GeometryUtil {

    /**
     * Computes the Euclidean distance between two points in 3D space.
     *
     * @param p1 the first point
     * @param p2 the second point
     * @return the distance between the two points
     */
    public static double computeDistance(Point p1, Point p2) {
        double dx = p2.getX() - p1.getX();
        double dy = p2.getY() - p1.getY();
        double dz = p2.getZ() - p1.getZ();

        return Math.sqrt(dx * dx + dy * dy + dz * dz);
    }

    /**
     * Computes the area of a triangle defined by three points in 3D space.
     * Uses the magnitude of the cross product of two vectors formed by the points.
     *
     * @param a the first point of the triangle
     * @param b the second point of the triangle
     * @param c the third point of the triangle
     * @return the area of the triangle
     */
    public static double computeTriangleArea(Point a, Point b, Point c) {

        Vector3D ab = new Vector3D(b.getX() - a.getX(), b.getY() - a.getY(), b.getZ() - a.getZ());
        Vector3D ac = new Vector3D(c.getX() - a.getX(), c.getY() - a.getY(), c.getZ() - a.getZ());

        Vector3D cross = VectorUtil.cross(ab, ac);

        return 0.5 * VectorUtil.magnitude(cross);
    }
}
