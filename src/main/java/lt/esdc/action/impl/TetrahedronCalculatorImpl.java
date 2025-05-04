package lt.esdc.action.impl;

import lt.esdc.action.ShapeCalculator;
import lt.esdc.entity.Point;
import lt.esdc.entity.Shape;
import lt.esdc.entity.Tetrahedron;
import lt.esdc.exception.ShapeValidationException;
import lt.esdc.util.GeometryUtil;
import lt.esdc.validator.ShapeValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Implementation of the ShapeCalculator interface for calculating properties (area, perimeter, volume) of a Tetrahedron.
 * This class uses a ShapeValidator to validate the Tetrahedron before performing any calculations.
 */
public class TetrahedronCalculatorImpl implements ShapeCalculator {
    private static final Logger logger = LogManager.getLogger(TetrahedronCalculatorImpl.class);

    private final ShapeValidator<Tetrahedron> tetrahedronValidator;

    /**
     * Constructs a TetrahedronCalculatorImpl instance with the specified Tetrahedron validator.
     *
     * @param tetrahedronValidator The validator used to validate the Tetrahedron.
     */
    public TetrahedronCalculatorImpl(ShapeValidator<Tetrahedron> tetrahedronValidator) {
        this.tetrahedronValidator = tetrahedronValidator;
    }

    /**
     * Validates the given shape and casts it to a Tetrahedron.
     * Throws a ShapeValidationException if the shape is null, not a Tetrahedron, or is invalid according to the validator.
     *
     * @param shape The shape to validate.
     * @return The validated Tetrahedron.
     * @throws ShapeValidationException if the shape is invalid or cannot be cast to a Tetrahedron.
     */
    private Tetrahedron validatedTetrahedron(Shape shape) throws ShapeValidationException { //TODO: possibly should throw different exceptions to distinguish between wrong shape and wrong input
        if (shape == null) {
            logger.error("Shape is null");
            throw new ShapeValidationException("Shape is null");
        }

        if (!(shape instanceof Tetrahedron tetrahedron)) {
            logger.error("Shape is not a Tetrahedron: {}", shape.getClass().getSimpleName());
            throw new ShapeValidationException("Shape is not a Tetrahedron");
        }

        if (!tetrahedronValidator.isValid(tetrahedron)) {
            logger.error("Tetrahedron is not valid");
            throw new ShapeValidationException("Tetrahedron is not valid");
        }

        return tetrahedron;
    }

    /**
     * Computes the surface area of the given Tetrahedron.
     * The area is calculated by summing the areas of the four triangular faces of the Tetrahedron.
     *
     * @param shape The shape (Tetrahedron) for which the area is to be computed.
     * @return The surface area of the Tetrahedron.
     * @throws ShapeValidationException if the shape is invalid or cannot be cast to a Tetrahedron.
     */
    @Override
    public double computeArea(Shape shape) throws ShapeValidationException {
        Tetrahedron tetrahedron = validatedTetrahedron(shape);

        Point a = tetrahedron.getPointA();
        Point b = tetrahedron.getPointB();
        Point c = tetrahedron.getPointC();
        Point d = tetrahedron.getPointD();

        double areaABC = GeometryUtil.computeTriangleArea(a, b, c);
        logger.info("Area ABC: {}", areaABC);
        double areaABD = GeometryUtil.computeTriangleArea(a, b, d);
        logger.info("Area ABD: {}", areaABD);
        double areaACD = GeometryUtil.computeTriangleArea(a, c, d);
        logger.info("Area ACD: {}", areaACD);
        double areaBCD = GeometryUtil.computeTriangleArea(b, c, d);
        logger.info("Area BCD: {}", areaBCD);

        return areaABC + areaABD + areaACD + areaBCD;
    }

    /**
     * Computes the perimeter of the given Tetrahedron.
     * The perimeter is calculated by summing the lengths of the six edges of the Tetrahedron.
     *
     * @param shape The shape (Tetrahedron) for which the perimeter is to be computed.
     * @return The perimeter of the Tetrahedron.
     * @throws ShapeValidationException if the shape is invalid or cannot be cast to a Tetrahedron.
     */
    @Override
    public double computePerimeter(Shape shape) throws ShapeValidationException {
        Tetrahedron tetrahedron = validatedTetrahedron(shape);

        Point a = tetrahedron.getPointA();
        Point b = tetrahedron.getPointB();
        Point c = tetrahedron.getPointC();
        Point d = tetrahedron.getPointD();

        double edgeAB = GeometryUtil.computeDistance(a, b);
        double edgeAC = GeometryUtil.computeDistance(a, c);
        double edgeAD = GeometryUtil.computeDistance(a, d);
        double edgeBC = GeometryUtil.computeDistance(b, c);
        double edgeBD = GeometryUtil.computeDistance(b, d);
        double edgeCD = GeometryUtil.computeDistance(c, d);

        return edgeAB + edgeAC + edgeAD + edgeBC + edgeBD + edgeCD;
    }

    /**
     * Computes the volume of the given Tetrahedron using the determinant method for a tetrahedron's volume.
     * The volume is computed using the scalar triple product of vectors formed by the Tetrahedron's points.
     *
     * @param shape The shape (Tetrahedron) for which the volume is to be computed.
     * @return The volume of the Tetrahedron.
     * @throws ShapeValidationException if the shape is invalid or cannot be cast to a Tetrahedron.
     */
    @Override
    public double computeVolume(Shape shape) throws ShapeValidationException {
        Tetrahedron tetrahedron = validatedTetrahedron(shape);

        Point a = tetrahedron.getPointA();
        Point b = tetrahedron.getPointB();
        Point c = tetrahedron.getPointC();
        Point d = tetrahedron.getPointD();

        double ax = b.getX() - a.getX();
        double ay = b.getY() - a.getY();
        double az = b.getZ() - a.getZ();

        double bx = c.getX() - a.getX();
        double by = c.getY() - a.getY();
        double bz = c.getZ() - a.getZ();

        double cx = d.getX() - a.getX();
        double cy = d.getY() - a.getY();
        double cz = d.getZ() - a.getZ();

        double crossX = ay * bz - az * by;
        double crossY = az * bx - ax * bz;
        double crossZ = ax * by - ay * bx;

        return Math.abs(crossX * cx + crossY * cy + crossZ * cz) / 6.0;
    }
}
