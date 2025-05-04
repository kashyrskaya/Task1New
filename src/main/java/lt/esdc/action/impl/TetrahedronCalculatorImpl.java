package lt.esdc.action.impl;

import lt.esdc.action.ShapeCalculator;
import lt.esdc.entity.Point;
import lt.esdc.entity.Shape;
import lt.esdc.entity.Tetrahedron;
import lt.esdc.exception.ShapeValidationException;
import lt.esdc.util.GeometryUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TetrahedronCalculatorImpl implements ShapeCalculator {
    private static final Logger logger = LogManager.getLogger(TetrahedronCalculatorImpl.class);

    @Override
    public double computeArea(Shape shape) throws ShapeValidationException {
        if (!(shape instanceof Tetrahedron tetrahedron)) {
            logger.error("Shape is not a Tetrahedron: {}", shape.getClass().getSimpleName());
            throw new ShapeValidationException("Shape is not a Tetrahedron");
        }

        Point a = tetrahedron.getPointA();
        Point b = tetrahedron.getPointB();
        Point c = tetrahedron.getPointC();
        Point d = tetrahedron.getPointD();

        double areaABC = GeometryUtil.computeTriangleArea(a, b, c);
        double areaABD = GeometryUtil.computeTriangleArea(a, b, d);
        double areaACD = GeometryUtil.computeTriangleArea(a, c, d);
        double areaBCD = GeometryUtil.computeTriangleArea(b, c, d);

        return areaABC + areaABD + areaACD + areaBCD;
    }

    @Override
    public double computePerimeter(Shape shape) throws ShapeValidationException {
        if (!(shape instanceof Tetrahedron tetrahedron)) {
            logger.error("Shape is not a Tetrahedron: {}", shape.getClass().getSimpleName());
            throw new ShapeValidationException("Shape is not a Tetrahedron");
        }

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

    @Override
    public double computeVolume(Shape shape) throws ShapeValidationException {
        if (!(shape instanceof Tetrahedron tetrahedron)) {
            logger.error("Shape is not a Tetrahedron: {}", shape.getClass().getSimpleName());
            throw new ShapeValidationException("Shape is not a Tetrahedron");
        }

        Point a = tetrahedron.getPointA();
        Point b = tetrahedron.getPointB();
        Point c = tetrahedron.getPointC();
        Point d = tetrahedron.getPointD();

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

        return Math.abs(crossX * cx + crossY * cy + crossZ * cz) / 6.0;
    }
}
