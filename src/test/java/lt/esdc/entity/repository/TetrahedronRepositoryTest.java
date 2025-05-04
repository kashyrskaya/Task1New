package lt.esdc.entity.repository;

import lt.esdc.action.impl.TetrahedronCalculatorImpl;
import lt.esdc.entity.Point;
import lt.esdc.entity.Tetrahedron;
import lt.esdc.entity.specification.impl.IdTetrahedronSpecification;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

import static org.mockito.Mockito.*;

public class TetrahedronRepositoryTest {

    private List<Tetrahedron> tetrahedrons;
    private TetrahedronRepository tetrahedronRepository;

    @BeforeMethod
    public void setUp() {
        tetrahedrons = mock(List.class);
        tetrahedronRepository = new TetrahedronRepository(tetrahedrons);
    }

    @Test
    public void testAdd() {
        Tetrahedron tetrahedron = new Tetrahedron("id", new Point(1,1,1), new Point(2,1,1), new Point(1,3,1), new Point(1,1,4));
        when(tetrahedrons.add(tetrahedron)).thenReturn(true);

        boolean result = tetrahedronRepository.add(tetrahedron);

        Assert.assertTrue(result);
        verify(tetrahedrons).add(tetrahedron);
    }

    @Test
    public void testRemove() {
        Tetrahedron tetrahedron = new Tetrahedron("id", new Point(1,1,1), new Point(2,1,1), new Point(1,3,1), new Point(1,1,4));
        when(tetrahedrons.remove(tetrahedron)).thenReturn(true);

        boolean result = tetrahedronRepository.remove(tetrahedron);

        Assert.assertTrue(result);
        verify(tetrahedrons).remove(tetrahedron);
    }

    @Test
    public void testQuery() {
        Tetrahedron tetrahedron = new Tetrahedron("id", new Point(1,1,1), new Point(2,1,1), new Point(1,3,1), new Point(1,1,4));
        Stream<Tetrahedron> streamMock = Stream.of(tetrahedron);
        when(tetrahedrons.stream()).thenReturn(streamMock);

        List<Tetrahedron> result = tetrahedronRepository.query(new IdTetrahedronSpecification("id"));

        Assert.assertEquals(result, List.of(tetrahedron));
        verify(tetrahedrons).stream();
    }

    @Test
    public void testSortByID() {
        tetrahedronRepository.sortByID();
        verify(tetrahedrons).sort(any(Comparator.class));
    }

    @Test
    public void testSortByArea() {
        TetrahedronCalculatorImpl calculatorMock = mock(TetrahedronCalculatorImpl.class);
        tetrahedronRepository.sortByArea(calculatorMock);
        verify(tetrahedrons).sort(any(Comparator.class));
    }

    @Test
    public void testSortByPerimeter() {
        TetrahedronCalculatorImpl calculatorMock = mock(TetrahedronCalculatorImpl.class);
        tetrahedronRepository.sortByPerimeter(calculatorMock);
        verify(tetrahedrons).sort(any(Comparator.class));
    }

    @Test
    public void testSortByVolume() {
        TetrahedronCalculatorImpl calculatorMock = mock(TetrahedronCalculatorImpl.class);
        tetrahedronRepository.sortByVolume(calculatorMock);
        verify(tetrahedrons).sort(any(Comparator.class));
    }

    @Test
    public void testGetAllTetrahedrons() {
        TetrahedronRepository repository = new TetrahedronRepository();
        Tetrahedron t = new Tetrahedron("id", new Point(1,1,1), new Point(2,1,1), new Point(1,3,1), new Point(1,1,4));

        repository.add(t);
        List<Tetrahedron> result = repository.getAllTetrahedrons();

        Assert.assertEquals(result, List.of(t));
    }
}
