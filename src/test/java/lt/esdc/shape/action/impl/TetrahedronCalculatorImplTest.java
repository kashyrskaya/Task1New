package lt.esdc.shape.action.impl;

import lt.esdc.shape.entity.Point;
import lt.esdc.shape.entity.Tetrahedron;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TetrahedronCalculatorImplTest {
    private TetrahedronCalculatorImpl calculator;
    private Tetrahedron validTetrahedron;

    @BeforeClass
    public void setUp() {
        calculator = new TetrahedronCalculatorImpl();

        validTetrahedron = new Tetrahedron(
                "valid",
                new Point(0, 0, 0),
                new Point(1, 0, 0),
                new Point(0, 1, 0),
                new Point(0, 0, 1)
        );
    }

    @Test
    public void testComputeArea_Valid() {
        double result = calculator.computeArea(validTetrahedron);
        Assert.assertTrue(result > 0, "Area should be positive for valid tetrahedron");
        Assert.assertEquals(result, 2.3660254037844386, 0.0001);
    }

    @Test
    public void testComputePerimeter_Valid() {
        double result = calculator.computePerimeter(validTetrahedron);
        Assert.assertTrue(result > 0, "Perimeter should be positive for valid tetrahedron");
        Assert.assertEquals(result, 7.242640687119285, 0.0001);
    }

    @Test
    public void testComputeVolume_Valid() {
        double result = calculator.computeVolume(validTetrahedron);
        Assert.assertTrue(result > 0, "Volume should be positive for valid tetrahedron");
        Assert.assertEquals(result, 1.0 / 6.0, 0.0001);
    }
}