package lt.esdc.shape.warehouse;

import lt.esdc.shape.action.ShapeCalculator;
import lt.esdc.shape.entity.Point;
import lt.esdc.shape.entity.Tetrahedron;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.mockito.Mockito.*;

public class WarehouseTest {

    private Warehouse warehouse;
    private ShapeCalculator calculatorMock;
    private Tetrahedron tetrahedron;

    @BeforeMethod
    public void setUp() {
        warehouse = Warehouse.getInstance();
        calculatorMock = mock(ShapeCalculator.class);

        tetrahedron = new Tetrahedron(
                "test_tetrahedron",
                new Point(0, 0, 0),
                new Point(1, 0, 0),
                new Point(0, 1, 0),
                new Point(0, 0, 1)
        );

        warehouse.registerCalculator(Tetrahedron.class, calculatorMock);
    }


    @Test
    public void testPutAndGetParameters() {

        double perimeter = 10.5;
        double area = 5.2;
        double volume = 1.3;

        warehouse.putParameters("test_id", perimeter, area, volume);


        Warehouse.ShapeParameters params = warehouse.getParameters("test_id");

        Assert.assertNotNull(params, "Parameters should not be null");
        Assert.assertEquals(params.getPerimeter(), perimeter, "Perimeter should match");
        Assert.assertEquals(params.getArea(), area, "Area should match");
        Assert.assertEquals(params.getVolume(), volume, "Volume should match");
    }

    @Test
    public void testContainsShape() {
        warehouse.putParameters("test_id", 1.0, 2.0, 3.0);

        Assert.assertTrue(warehouse.containsShape("test_id"), "Warehouse should contain the shape");
        Assert.assertFalse(warehouse.containsShape("non_existent_id"), "Warehouse should not contain a non-existent shape");
    }

    @Test
    public void testRemove() {
        warehouse.putParameters("test_id", 1.0, 2.0, 3.0);
        warehouse.remove("test_id");

        Assert.assertFalse(warehouse.containsShape("test_id"), "Shape should be removed from warehouse");
    }

    @Test
    public void testUpdate() {

        when(calculatorMock.computePerimeter(tetrahedron)).thenReturn(6.0);
        when(calculatorMock.computeArea(tetrahedron)).thenReturn(1.7320508076);
        when(calculatorMock.computeVolume(tetrahedron)).thenReturn(0.16666666667);


        warehouse.update(tetrahedron);


        verify(calculatorMock).computePerimeter(tetrahedron);
        verify(calculatorMock).computeArea(tetrahedron);
        verify(calculatorMock).computeVolume(tetrahedron);


        Warehouse.ShapeParameters params = warehouse.getParameters("test_tetrahedron");
        Assert.assertNotNull(params, "Parameters should not be null after update");
        Assert.assertEquals(params.getPerimeter(), 6.0, 0.0001, "Perimeter should match");
        Assert.assertEquals(params.getArea(), 1.7320508076, 0.0001, "Area should match");
        Assert.assertEquals(params.getVolume(), 0.16666666667, 0.0001, "Volume should match");
    }

    @Test
    public void testUpdateWithNullShape() {

        warehouse.update(null);

        verifyNoInteractions(calculatorMock);
    }

    @Test
    public void testUpdateWithUnregisteredShape() {
        Warehouse freshWarehouse = Warehouse.getInstance();

        freshWarehouse.update(tetrahedron);
    }

    @Test
    public void testGetNonExistentParameters() {
        Warehouse.ShapeParameters params = warehouse.getParameters("non_existent_id");
        Assert.assertNull(params, "Parameters for non-existent shape should be null");
    }

    @Test
    public void testSingletonInstance() {
        Warehouse instance1 = Warehouse.getInstance();
        Warehouse instance2 = Warehouse.getInstance();

        Assert.assertSame(instance1, instance2, "getInstance should return the same instance");
    }

    @Test
    public void testShapeParametersToString() {
        Warehouse.ShapeParameters params = new Warehouse.ShapeParameters(10.0, 20.0, 30.0);
        String toString = params.toString();

        Assert.assertTrue(toString.contains("perimeter=10.0"), "ToString should contain perimeter");
        Assert.assertTrue(toString.contains("area=20.0"), "ToString should contain area");
        Assert.assertTrue(toString.contains("volume=30.0"), "ToString should contain volume");
    }
}