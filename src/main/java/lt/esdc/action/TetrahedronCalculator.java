package lt.esdc.action;

import lt.esdc.entity.Point;
import lt.esdc.entity.Tetrahedron;
import lt.esdc.util.GeometryUtil;

public class TetrahedronCalculator extends ShapeCalculator<Tetrahedron> {
    @Override
    public double computeArea(Tetrahedron shape) {
        Point a = shape.getPointA();
        Point b = shape.getPointB();
        Point c = shape.getPointC();
        Point d = shape.getPointD();

        double areaABC = GeometryUtil.computeTriangleArea(a, b, c);
        double areaABD = GeometryUtil.computeTriangleArea(a, b, d);
        double areaACD = GeometryUtil.computeTriangleArea(a, c, d);
        double areaBCD = GeometryUtil.computeTriangleArea(b, c, d);

        return areaABC + areaABD + areaACD + areaBCD;
    }

    @Override
    public double computePerimeter(Tetrahedron shape) {
        Point a = shape.getPointA();
        Point b = shape.getPointB();
        Point c = shape.getPointC();
        Point d = shape.getPointD();

        double edgeAB = GeometryUtil.computeDistance(a, b);
        double edgeAC = GeometryUtil.computeDistance(a, c);
        double edgeAD = GeometryUtil.computeDistance(a, d);
        double edgeBC = GeometryUtil.computeDistance(b, c);
        double edgeBD = GeometryUtil.computeDistance(b, d);
        double edgeCD = GeometryUtil.computeDistance(c, d);

        return edgeAB + edgeAC + edgeAD + edgeBC + edgeBD + edgeCD;
    }

    public double computeVolume(Tetrahedron tetrahedron) {
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
