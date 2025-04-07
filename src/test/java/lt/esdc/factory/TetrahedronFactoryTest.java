package lt.esdc.factory;

import lt.esdc.entity.Tetrahedron;
import lt.esdc.exception.ShapeValidationException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.Assert.assertNotNull;

public class TetrahedronFactoryTest {

    private TetrahedronFactory factory;

    @BeforeClass
    public void setUp() {
        factory = new TetrahedronFactory();
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
