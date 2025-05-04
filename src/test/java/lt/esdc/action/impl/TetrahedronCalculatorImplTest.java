package lt.esdc.action.impl;

import lt.esdc.entity.Point;
import lt.esdc.entity.Tetrahedron;
import lt.esdc.exception.ShapeValidationException;
import lt.esdc.validator.TetrahedronValidatorImpl;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TetrahedronCalculatorImplTest {
    private TetrahedronCalculatorImpl calculator;
    private Tetrahedron validTetrahedron;
    private Tetrahedron degenerateTetrahedron; // плоский или с коллапсированными вершинами

    @BeforeClass
    public void setUp() {
        // Use TetrahedronValidatorImpl as required by the constructor
        TetrahedronValidatorImpl validator = new TetrahedronValidatorImpl();
        calculator = new TetrahedronCalculatorImpl(validator);

        validTetrahedron = new Tetrahedron(
                "valid",
                new Point(0, 0, 0),
                new Point(1, 0, 0),
                new Point(0, 1, 0),
                new Point(0, 0, 1)
        );

        degenerateTetrahedron = new Tetrahedron(
                "degenerate",
                new Point(0, 0, 0),
                new Point(1, 0, 0),
                new Point(2, 0, 0),
                new Point(3, 0, 0)
        );
    }

    @Test
    public void testComputeArea_Valid() throws ShapeValidationException {
        double result = calculator.computeArea(validTetrahedron);
        Assert.assertTrue(result > 0, "Area should be positive for valid tetrahedron");
        Assert.assertEquals(result, 2.3660254037844386, 0.0001);
    }

    @Test
    public void testComputePerimeter_Valid() throws ShapeValidationException {
        double result = calculator.computePerimeter(validTetrahedron);
        Assert.assertTrue(result > 0, "Perimeter should be positive for valid tetrahedron");
        Assert.assertEquals(result, 7.242640687119285, 0.0001);
    }

    @Test
    public void testComputeVolume_Valid() throws ShapeValidationException {
        double result = calculator.computeVolume(validTetrahedron);
        Assert.assertTrue(result > 0, "Volume should be positive for valid tetrahedron");
        Assert.assertEquals(result, 1.0 / 6.0, 0.0001);
    }

    @Test(expectedExceptions = ShapeValidationException.class)
    public void testComputeArea_Degenerate() throws ShapeValidationException {
        // Should throw an exception because it's not a valid tetrahedron
        calculator.computeArea(degenerateTetrahedron);
    }

    @Test(expectedExceptions = ShapeValidationException.class)
    public void testComputePerimeter_Degenerate() throws ShapeValidationException {
        // Should throw an exception because it's not a valid tetrahedron
        calculator.computePerimeter(degenerateTetrahedron);
    }

    @Test(expectedExceptions = ShapeValidationException.class)
    public void testComputeVolume_Degenerate() throws ShapeValidationException {
        // Should throw an exception because it's not a valid tetrahedron
        calculator.computeVolume(degenerateTetrahedron);
    }

    @Test(expectedExceptions = ShapeValidationException.class)
    public void testComputeArea_NullInput() throws ShapeValidationException {
        calculator.computeArea(null);
    }

    @Test(expectedExceptions = ShapeValidationException.class)
    public void testComputePerimeter_NullInput() throws ShapeValidationException {
        calculator.computePerimeter(null);
    }

    @Test(expectedExceptions = ShapeValidationException.class)
    public void testComputeVolume_NullInput() throws ShapeValidationException {
        calculator.computeVolume(null);
    }
}