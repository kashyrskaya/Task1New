package lt.esdc.validator;

import lt.esdc.entity.Point;
import lt.esdc.entity.Tetrahedron;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TetrahedronValidatorImplTest {

    private TetrahedronValidatorImpl validator;

    @BeforeClass
    public void setUp() {
        validator = new TetrahedronValidatorImpl();
    }

    @Test
    public void testValidTetrahedron() {
        // Regular tetrahedron
        Tetrahedron tetrahedron = new Tetrahedron(
                "valid",
                new Point(0, 0, 0),
                new Point(1, 0, 0),
                new Point(0, 1, 0),
                new Point(0, 0, 1)
        );

        boolean result = validator.isValid(tetrahedron);
        Assert.assertTrue(result, "Regular tetrahedron should be valid");
    }

    @Test
    public void testCoplanarPoints() {
        // All points lie in the XY plane
        Tetrahedron tetrahedron = new Tetrahedron(
                "coplanar",
                new Point(0, 0, 0),
                new Point(1, 0, 0),
                new Point(0, 1, 0),
                new Point(1, 1, 0)
        );

        boolean result = validator.isValid(tetrahedron);
        Assert.assertFalse(result, "Tetrahedron with coplanar points should be invalid");
    }

    @Test
    public void testDuplicatePoints() {
        // Two points are the same
        Tetrahedron tetrahedron = new Tetrahedron(
                "duplicate",
                new Point(0, 0, 0),
                new Point(0, 0, 0),  // Same as first point
                new Point(0, 1, 0),
                new Point(0, 0, 1)
        );

        boolean result = validator.isValid(tetrahedron);
        Assert.assertFalse(result, "Tetrahedron with duplicate points should be invalid");
    }

    @Test
    public void testCollinearPoints() {
        // Three points are collinear
        Tetrahedron tetrahedron = new Tetrahedron(
                "collinear",
                new Point(0, 0, 0),
                new Point(1, 0, 0),
                new Point(2, 0, 0),  // Collinear with first two points
                new Point(0, 0, 1)
        );

        boolean result = validator.isValid(tetrahedron);
        Assert.assertFalse(result, "Tetrahedron with collinear points should be invalid");
    }

    @Test
    public void testZeroVolumeTetrahedron() {
        // All points form a flat shape
        Tetrahedron tetrahedron = new Tetrahedron(
                "zero_volume",
                new Point(0, 0, 0),
                new Point(1, 0, 0),
                new Point(0, 1, 0),
                new Point(1, 1, 0)  // All points in XY plane
        );

        boolean result = validator.isValid(tetrahedron);
        Assert.assertFalse(result, "Tetrahedron with zero volume should be invalid");
    }
}