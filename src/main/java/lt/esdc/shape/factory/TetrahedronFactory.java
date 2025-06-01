package lt.esdc.shape.factory;

import lt.esdc.shape.entity.Point;
import lt.esdc.shape.entity.Tetrahedron;

import java.util.List;
import java.util.UUID;

/**
 * Factory class for creating Tetrahedron objects.
 */
public class TetrahedronFactory implements ShapeFactory {

    /**
     * Creates a Tetrahedron object from the specified parameters.
     *
     * @param parameters an array of strings representing the coordinates of the Tetrahedron's points
     * @return the created Tetrahedron object
     */
    @Override
    public Tetrahedron createShape(List<Double> parameters) {
            Point pointA = new Point(parameters.get(0), parameters.get(1), parameters.get(2));
            Point pointB = new Point(parameters.get(3), parameters.get(4), parameters.get(5));
            Point pointC = new Point(parameters.get(6), parameters.get(7), parameters.get(8));
            Point pointD = new Point(parameters.get(9), parameters.get(10), parameters.get(11));

            String id = "tetrahedron_" + UUID.randomUUID();

            return new Tetrahedron(id, pointA, pointB, pointC, pointD);
    }

}
