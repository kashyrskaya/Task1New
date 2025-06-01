package lt.esdc.shape.factory;

import lt.esdc.shape.entity.Tetrahedron;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

import static org.testng.Assert.assertNotNull;

public class TetrahedronFactoryTest {

    private TetrahedronFactory factory;
    private List<Double> params;

    @BeforeClass
    public void setUp() {
        factory = new TetrahedronFactory();
        params = Arrays.asList(0.0, 0., 2.0,
                1.0, 0.0, 0.0,
                0.0, 1.0, 0.0,
                0.0, 0.0, 1.0);
    }

    @Test
    public void testCreateValidTetrahedron() {

        Tetrahedron tetrahedron = factory.createShape(params);

        assertNotNull(tetrahedron);
    }
}
