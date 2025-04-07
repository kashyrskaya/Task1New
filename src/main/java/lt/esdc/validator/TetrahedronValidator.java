package lt.esdc.validator;

import lt.esdc.action.TetrahedronCalculator;
import lt.esdc.entity.Point;
import lt.esdc.entity.Tetrahedron;

public class TetrahedronValidator implements ShapeValidator<Tetrahedron> {

    @Override
    public boolean isValid(Tetrahedron tetrahedron) {
        Point a = tetrahedron.getPointA();
        Point b = tetrahedron.getPointB();
        Point c = tetrahedron.getPointC();
        Point d = tetrahedron.getPointD();

        if (a.equals(b) || a.equals(c) || a.equals(d) ||
                b.equals(c) || b.equals(d) || c.equals(d)) {
            return false;
        }

        TetrahedronCalculator calculator = new TetrahedronCalculator();

        return calculator.computeVolume(tetrahedron) != 0;
    }
}
