package lt.esdc.shape.factory;

import lt.esdc.shape.entity.Tetrahedron;
import lt.esdc.shape.exception.ShapeValidationException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.Assert.assertNotNull;

public class TetrahedronFactoryTest {

    private TetrahedronFactoryAbstract factory;

    @BeforeClass
    public void setUp() {
        factory = new TetrahedronFactoryAbstract();
    }

    @Test
    public void testCreateValidTetrahedron() throws ShapeValidationException {
        String[] params = {"0.0", "0.0", "0.0",
                "1.0", "0.0", "0.0",
                "0.0", "1.0", "0.0",
                "0.0", "0.0", "1.0"};

        Tetrahedron tetrahedron = (Tetrahedron) factory.createShape(params);

        assertNotNull(tetrahedron);
    }
}
