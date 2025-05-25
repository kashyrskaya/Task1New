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
        double dx = p2.x() - p1.x();
        double dy = p2.y() - p1.y();
        double dz = p2.z() - p1.z();

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

        Vector3D ab = new Vector3D(b.x() - a.x(), b.y() - a.y(), b.z() - a.z());
        Vector3D ac = new Vector3D(c.x() - a.x(), c.y() - a.y(), c.z() - a.z());

        Vector3D cross = VectorUtil.cross(ab, ac);

        return 0.5 * VectorUtil.magnitude(cross);
    }
}
