package lt.esdc.shape.util;

import lt.esdc.shape.entity.Point;
import lt.esdc.shape.util.action.GeometryUtil;
import org.testng.Assert;
import org.testng.annotations.Test;

public class GeometryUtilTest {

    @Test
    public void testComputeDistance_SamePoint() {
        Point p1 = new Point(1, 1, 1);
        Point p2 = new Point(1, 1, 1);

        double distance = GeometryUtil.computeDistance(p1, p2);
        Assert.assertEquals(distance, 0.0, "Distance between same points should be 0");
    }

    @Test
    public void testComputeDistance_XAxisOnly() {
        Point p1 = new Point(0, 0, 0);
        Point p2 = new Point(3, 0, 0);

        double distance = GeometryUtil.computeDistance(p1, p2);
        Assert.assertEquals(distance, 3.0, "Distance should be 3 for points (0,0,0) and (3,0,0)");
    }

    @Test
    public void testComputeDistance_3D() {
        Point p1 = new Point(1, 2, 3);
        Point p2 = new Point(4, 6, 8);

        double distance = GeometryUtil.computeDistance(p1, p2);
        double expected = Math.sqrt(50); // sqrt((4-1)² + (6-2)² + (8-3)²) = sqrt(9 + 16 + 25) = sqrt(50)
        Assert.assertEquals(distance, expected, 0.0001, "Distance calculation is incorrect");
    }

    @Test
    public void testComputeTriangleArea_Regular() {
        // Creating an equilateral triangle in the XY plane with side length 2
        Point a = new Point(0, 0, 0);
        Point b = new Point(2, 0, 0);
        Point c = new Point(1, Math.sqrt(3), 0);

        double area = GeometryUtil.computeTriangleArea(a, b, c);
        double expected = Math.sqrt(3); // Area of equilateral triangle = (sqrt(3)/4) * side²
        Assert.assertEquals(area, expected, 0.0001, "Triangle area calculation is incorrect");
    }

    @Test
    public void testComputeTriangleArea_Degenerate() {
        // Creating a degenerate triangle (three points on a line)
        Point a = new Point(0, 0, 0);
        Point b = new Point(1, 0, 0);
        Point c = new Point(2, 0, 0);

        double area = GeometryUtil.computeTriangleArea(a, b, c);
        Assert.assertEquals(area, 0.0, 0.0001, "Area of degenerate triangle should be 0");
    }

    @Test
    public void testComputeTriangleArea_3D() {
        // Triangle in 3D space
        Point a = new Point(0, 0, 0);
        Point b = new Point(1, 0, 0);
        Point c = new Point(0, 1, 1);

        double area = GeometryUtil.computeTriangleArea(a, b, c);
        double expected = Math.sqrt(2) / 2;
        Assert.assertEquals(area, expected, 0.0001, "3D triangle area calculation is incorrect");
    }
}