package lt.esdc.factory;

import lt.esdc.entity.Point;
import lt.esdc.entity.Shape;
import lt.esdc.entity.Tetrahedron;
import lt.esdc.exception.ShapeValidationException;

import java.util.UUID;

public class TetrahedronFactory extends ShapeFactory {
    private static final int REQUIRED_PARAMS = 8;

    @Override
    public Shape createShape(String[] parameters) throws ShapeValidationException {
        if (parameters.length != REQUIRED_PARAMS) {
            throw new ShapeValidationException("Invalid number of parameters for tetrahedron: " + parameters.length);
        }

        try {
            double x1 = Double.parseDouble(parameters[0]);
            double y1 = Double.parseDouble(parameters[1]);
            double x2 = Double.parseDouble(parameters[2]);
            double y2 = Double.parseDouble(parameters[3]);
            double x3 = Double.parseDouble(parameters[4]);
            double y3 = Double.parseDouble(parameters[5]);
            double x4 = Double.parseDouble(parameters[6]);
            double y4 = Double.parseDouble(parameters[7]);

            Point pointA = new Point(x1, y1);
            Point pointB = new Point(x2, y2);
            Point pointC = new Point(x3, y3);
            Point pointD = new Point(x4, y4);

            String id = "tetrahedron_" + UUID.randomUUID();

            return new Tetrahedron(id, pointA, pointB, pointC, pointD);
        } catch (NumberFormatException e) {
            throw new ShapeValidationException("Invalid parameters format for tetrahedron: ", e);
        }
    }

}
