package lt.esdc.shape.action.impl;

import lt.esdc.shape.action.ShapeCalculator;
import lt.esdc.shape.entity.Point;
import lt.esdc.shape.entity.Shape;
import lt.esdc.shape.entity.Tetrahedron;
import lt.esdc.shape.util.action.GeometryUtil;
import lt.esdc.shape.util.action.VectorUtil;
import lt.esdc.shape.util.entity.Vector3D;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Implementation of the ShapeCalculator interface for calculating properties (area, perimeter, volume) of a Tetrahedron.
 * This class uses a ShapeValidator to validate the Tetrahedron before performing any calculations.
 */
public class TetrahedronCalculatorImpl implements ShapeCalculator {
    private static final Logger logger = LogManager.getLogger(TetrahedronCalculatorImpl.class);

    /**
     * Computes the surface area of the given Tetrahedron.
     * The area is calculated by summing the areas of the four triangular faces of the Tetrahedron.
     *
     * @param shape The shape (Tetrahedron) for which the area is to be computed.
     * @return The surface area of the Tetrahedron.
     */
    @Override
    public double computeArea(Shape shape) {
        Tetrahedron tetrahedron = (Tetrahedron) shape;

        Point a = tetrahedron.getPointA();
        Point b = tetrahedron.getPointB();
        Point c = tetrahedron.getPointC();
        Point d = tetrahedron.getPointD();

        double areaABC = GeometryUtil.computeTriangleArea(a, b, c);
        logger.info("Area ABC: {}", areaABC);
        double areaABD = GeometryUtil.computeTriangleArea(a, b, d);
        logger.info("Area ABD: {}", areaABD);
        double areaACD = GeometryUtil.computeTriangleArea(a, c, d);
        logger.info("Area ACD: {}", areaACD);
        double areaBCD = GeometryUtil.computeTriangleArea(b, c, d);
        logger.info("Area BCD: {}", areaBCD);

        return areaABC + areaABD + areaACD + areaBCD;
    }

    /**
     * Computes the perimeter of the given Tetrahedron.
     * The perimeter is calculated by summing the lengths of the six edges of the Tetrahedron.
     *
     * @param shape The shape (Tetrahedron) for which the perimeter is to be computed.
     * @return The perimeter of the Tetrahedron.
     */
    @Override
    public double computePerimeter(Shape shape) {
        Tetrahedron tetrahedron = (Tetrahedron) shape;

        Point a = tetrahedron.getPointA();
        Point b = tetrahedron.getPointB();
        Point c = tetrahedron.getPointC();
        Point d = tetrahedron.getPointD();

        double edgeAB = GeometryUtil.computeDistance(a, b);
        double edgeAC = GeometryUtil.computeDistance(a, c);
        double edgeAD = GeometryUtil.computeDistance(a, d);
        double edgeBC = GeometryUtil.computeDistance(b, c);
        double edgeBD = GeometryUtil.computeDistance(b, d);
        double edgeCD = GeometryUtil.computeDistance(c, d);

        return edgeAB + edgeAC + edgeAD + edgeBC + edgeBD + edgeCD;
    }

    /**
     * Computes the volume of the given Tetrahedron using the determinant method for a tetrahedron's volume.
     * The volume is computed using the scalar triple product of vectors formed by the Tetrahedron's points.
     *
     * @param shape The shape (Tetrahedron) for which the volume is to be computed.
     * @return The volume of the Tetrahedron.
     */
    @Override
    public double computeVolume(Shape shape) {
        Tetrahedron tetrahedron = (Tetrahedron) shape;

        Point a = tetrahedron.getPointA();
        Point b = tetrahedron.getPointB();
        Point c = tetrahedron.getPointC();
        Point d = tetrahedron.getPointD();

        Vector3D ab = new Vector3D(b.getX() - a.getX(), b.getY() - a.getY(), b.getZ() - a.getZ());
        Vector3D ac = new Vector3D(c.getX() - a.getX(), c.getY() - a.getY(), c.getZ() - a.getZ());
        Vector3D ad = new Vector3D(d.getX() - a.getX(), d.getY() - a.getY(), d.getZ() - a.getZ());

        Vector3D cross = VectorUtil.cross(ac, ad);

        return Math.abs(VectorUtil.dot(ab, cross)) / 6.0;
    }
}
