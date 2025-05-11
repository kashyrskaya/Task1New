package lt.esdc.shape.repository;

import lt.esdc.shape.entity.Point;
import lt.esdc.shape.entity.Shape;
import lt.esdc.shape.entity.Tetrahedron;
import lt.esdc.shape.specification.impl.IdTetrahedronSpecification;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.List;

public class TetrahedronRepositoryTest {

    private TetrahedronRepository tetrahedronRepository;

    @BeforeMethod
    public void setUp() {
        tetrahedronRepository = new TetrahedronRepository();
    }

    @Test
    public void testAdd() {
        Tetrahedron tetrahedron = new Tetrahedron("id", new Point(1, 1, 1), new Point(2, 1, 1), new Point(1, 3, 1), new Point(1, 1, 4));

        boolean result = tetrahedronRepository.add(tetrahedron);

        Assert.assertTrue(result);

        List<Tetrahedron> allTetrahedrons = tetrahedronRepository.getAllTetrahedrons();
        Assert.assertTrue(allTetrahedrons.contains(tetrahedron));
    }

    @Test
    public void testRemove() {
        Tetrahedron tetrahedron = new Tetrahedron("id", new Point(1, 1, 1), new Point(2, 1, 1), new Point(1, 3, 1), new Point(1, 1, 4));

        tetrahedronRepository.add(tetrahedron);

        boolean result = tetrahedronRepository.remove(tetrahedron);

        Assert.assertTrue(result);

        List<Tetrahedron> allTetrahedrons = tetrahedronRepository.getAllTetrahedrons();
        Assert.assertFalse(allTetrahedrons.contains(tetrahedron));
    }

    @Test
    public void testQuery() {
        Tetrahedron tetrahedron = new Tetrahedron("id", new Point(1, 1, 1), new Point(2, 1, 1), new Point(1, 3, 1), new Point(1, 1, 4));
        tetrahedronRepository.add(tetrahedron);

        IdTetrahedronSpecification specification = new IdTetrahedronSpecification("id");

        List<Tetrahedron> result = tetrahedronRepository.query(specification);

        Assert.assertTrue(result.contains(tetrahedron));
    }

    @Test
    public void testSortByID() {
        Tetrahedron tetrahedron1 = new Tetrahedron("id2", new Point(1, 1, 1), new Point(2, 1, 1), new Point(1, 3, 1), new Point(1, 1, 4));
        Tetrahedron tetrahedron2 = new Tetrahedron("id1", new Point(1, 1, 1), new Point(2, 1, 1), new Point(1, 3, 1), new Point(1, 1, 4));

        tetrahedronRepository.add(tetrahedron1);
        tetrahedronRepository.add(tetrahedron2);

        tetrahedronRepository.sortBy(Comparator.comparing(Shape::getId));

        List<Tetrahedron> sortedTetrahedrons = tetrahedronRepository.getAllTetrahedrons();

        Assert.assertEquals(sortedTetrahedrons.get(0), tetrahedron2);
        Assert.assertEquals(sortedTetrahedrons.get(1), tetrahedron1);
    }

    @Test
    public void testGetAllTetrahedrons() {
        Tetrahedron tetrahedron = new Tetrahedron("id", new Point(1, 1, 1), new Point(2, 1, 1), new Point(1, 3, 1), new Point(1, 1, 4));
        tetrahedronRepository.add(tetrahedron);

        List<Tetrahedron> result = tetrahedronRepository.getAllTetrahedrons();

        Assert.assertTrue(result.contains(tetrahedron));
    }
}
