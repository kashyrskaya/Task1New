package lt.esdc.shape.repository;

import lt.esdc.shape.entity.Point;
import lt.esdc.shape.entity.Tetrahedron;
import lt.esdc.shape.specification.impl.IdTetrahedronSpecification;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class TetrahedronRepositoryTest {

    private TetrahedronRepository tetrahedronRepository;
    private Tetrahedron tetrahedronOne;
    private Tetrahedron tetrahedronThree;
    private IdTetrahedronSpecification specification;

    @BeforeMethod
    public void setUp() {
        tetrahedronRepository = TetrahedronRepository.getInstance();
        tetrahedronOne = new Tetrahedron("id1", new Point(1, 1, 1), new Point(2, 1, 1), new Point(1, 3, 1), new Point(1, 1, 4));
        tetrahedronThree = new Tetrahedron("id3", new Point(1, 1, 1), new Point(2, 1, 1), new Point(1, 3, 1), new Point(1, 1, 4));
        specification = new IdTetrahedronSpecification("id1");
    }

    @Test
    public void testAdd() {
        boolean result = tetrahedronRepository.add(tetrahedronOne);

        Assert.assertTrue(result);

        List<Tetrahedron> allTetrahedrons = tetrahedronRepository.getAllTetrahedrons();
        Assert.assertTrue(allTetrahedrons.contains(tetrahedronOne));
    }

    @Test
    public void testRemove() {
        tetrahedronRepository.add(tetrahedronThree);

        boolean result = tetrahedronRepository.remove(tetrahedronThree);

        Assert.assertTrue(result);

        List<Tetrahedron> allTetrahedrons = tetrahedronRepository.getAllTetrahedrons();
        Assert.assertFalse(allTetrahedrons.contains(tetrahedronThree));
    }

    @Test
    public void testQuery() {
        tetrahedronRepository.add(tetrahedronOne);

        List<Tetrahedron> result = tetrahedronRepository.query(specification);

        Assert.assertTrue(result.contains(tetrahedronOne));
    }

    @Test
    public void testGetAllTetrahedrons() {
        tetrahedronRepository.add(tetrahedronOne);

        List<Tetrahedron> result = tetrahedronRepository.getAllTetrahedrons();

        Assert.assertTrue(result.contains(tetrahedronOne));
    }
}
