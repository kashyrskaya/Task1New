package lt.esdc.shape.action.impl;

import lt.esdc.shape.action.ShapeCalculator;
import lt.esdc.shape.entity.AbstractShape;
import lt.esdc.shape.entity.Point;
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
     * @param abstractShape The shape (Tetrahedron) for which the area is to be computed.
     * @return The surface area of the Tetrahedron.
     */
    @Override
    public double computeArea(AbstractShape abstractShape) {
        Tetrahedron tetrahedron = (Tetrahedron) abstractShape;

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
     * @param abstractShape The shape (Tetrahedron) for which the perimeter is to be computed.
     * @return The perimeter of the Tetrahedron.
     */
    @Override
    public double computePerimeter(AbstractShape abstractShape) {
        Tetrahedron tetrahedron = (Tetrahedron) abstractShape;

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
     * @param abstractShape The shape (Tetrahedron) for which the volume is to be computed.
     * @return The volume of the Tetrahedron.
     */
    @Override
    public double computeVolume(AbstractShape abstractShape) {
        Tetrahedron tetrahedron = (Tetrahedron) abstractShape;

        Point a = tetrahedron.getPointA();
        Point b = tetrahedron.getPointB();
        Point c = tetrahedron.getPointC();
        Point d = tetrahedron.getPointD();

        Vector3D ab = new Vector3D(b.x() - a.x(), b.y() - a.y(), b.z() - a.z());
        Vector3D ac = new Vector3D(c.x() - a.x(), c.y() - a.y(), c.z() - a.z());
        Vector3D ad = new Vector3D(d.x() - a.x(), d.y() - a.y(), d.z() - a.z());

        Vector3D cross = VectorUtil.cross(ac, ad);

        return Math.abs(VectorUtil.dot(ab, cross)) / 6.0;
    }
}
