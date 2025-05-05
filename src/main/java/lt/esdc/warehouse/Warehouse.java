package lt.esdc.warehouse;

import lt.esdc.action.ShapeCalculator;
import lt.esdc.entity.Shape;
import lt.esdc.observer.ShapeObserver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

/**
 * Singleton class for managing Shape parameters and calculators.
 * Acts as a central repository for storing and retrieving Shape-related data.
 * Implements the ShapeObserver interface to update Shape parameters when changes occur.
 */
public class Warehouse implements ShapeObserver {
    private static final Logger logger = LogManager.getLogger(Warehouse.class);

    private static Warehouse instance;
    private final Map<String, ShapeParameters> parametersMap;
    private final Map<Class<? extends Shape>, ShapeCalculator> calculatorMap;

    /**
     * Private constructor to enforce the Singleton pattern.
     * Initializes the parameters and calculator maps.
     */
    private Warehouse() {
        parametersMap = new HashMap<>();
        calculatorMap = new HashMap<>();
        logger.info("Warehouse created");
    }

    /**
     * Returns the Singleton instance of the Warehouse.
     * Note: This implementation is not thread-safe.
     *
     * @return the Singleton instance of the Warehouse
     */
    public static Warehouse getInstance() {
        if (instance == null) {
            instance = new Warehouse();
        }
        return instance;
    }

    /**
     * Registers a ShapeCalculator for a specific Shape type.
     *
     * @param shapeClass the class of the Shape
     * @param calculator the ShapeCalculator to register
     */
    public void registerCalculator(Class<? extends Shape> shapeClass, ShapeCalculator calculator) {
        calculatorMap.put(shapeClass, calculator);
        logger.debug("Calculator for {} registered in Warehouse", shapeClass.getSimpleName());
    }

    /**
     * Stores the parameters (perimeter, area, volume) for a Shape with the given ID.
     *
     * @param shapeId the ID of the Shape
     * @param perimeter the perimeter of the Shape
     * @param area the area of the Shape
     * @param volume the volume of the Shape
     */
    public void putParameters(String shapeId, double perimeter, double area, double volume) {
        ShapeParameters parameters = new ShapeParameters(perimeter, area, volume);
        parametersMap.put(shapeId, parameters);
        logger.debug("Parameters for shape {} saved: {}", shapeId, parameters);
    }

    /**
     * Retrieves the parameters for a Shape with the given ID.
     *
     * @param shapeId the ID of the Shape
     * @return the ShapeParameters object, or null if not found
     */
    public ShapeParameters getParameters(String shapeId) {
        ShapeParameters parameters = parametersMap.get(shapeId);
        if (parameters == null) {
            logger.warn("No parameters found for shape {}", shapeId);
        }
        return parameters;
    }

    /**
     * Checks if the Warehouse contains parameters for a Shape with the given ID.
     *
     * @param shapeId the ID of the Shape
     * @return true if the parameters exist, false otherwise
     */
    public boolean containsShape(String shapeId) {
        return parametersMap.containsKey(shapeId);
    }

    /**
     * Removes the parameters for a Shape with the given ID.
     *
     * @param shapeId the ID of the Shape
     */
    public void remove(String shapeId) {
        parametersMap.remove(shapeId);
        logger.debug("Parameters for shape {} removed", shapeId);
    }

    /**
     * Updates the parameters of a Shape when notified of changes.
     *
     * @param shape the Shape that was updated
     */
    @Override
    public void update(Shape shape) {
        logger.debug("Observer received update for shape {}", shape);

        if (shape == null) {
            return;
        }

        try {
            String shapeId = shape.getId();

            ShapeCalculator calculator = getCalculatorForShape(shape);

            if (calculator == null) {
                logger.error("No service registered for shape type: {}", shape.getClass().getSimpleName());
                return;
            }

            double perimeter = calculator.computePerimeter(shape);
            double area = calculator.computeArea(shape);
            double volume = calculator.computeVolume(shape);

            putParameters(shapeId, perimeter, area, volume);
        } catch (Exception e) {
            logger.error("Error while updating shape {}", shape);
        }
    }

    /**
     * Retrieves the ShapeCalculator for the given Shape.
     * Searches for a calculator registered for the Shape's class or its superclass.
     *
     * @param shape the Shape to find a calculator for
     * @return the ShapeCalculator, or null if none is found
     */
    private ShapeCalculator getCalculatorForShape(Shape shape) {
        ShapeCalculator calculator = calculatorMap.get(shape.getClass());

        if (calculator == null) {
            for (Map.Entry<Class<? extends Shape>, ShapeCalculator> entry : calculatorMap.entrySet()) {
                if (entry.getKey().isAssignableFrom(shape.getClass())) {
                    return entry.getValue();
                }
            }
        }

        return calculator;
    }

    /**
     * Inner class representing the parameters of a Shape.
     * Stores the perimeter, area, and volume of a Shape.
     */
    public static class ShapeParameters {
        private final double perimeter;
        private final double area;
        private final double volume;

        /**
         * Constructs a ShapeParameters object with the specified values.
         *
         * @param perimeter the perimeter of the Shape
         * @param area the area of the Shape
         * @param volume the volume of the Shape
         */
        public ShapeParameters(double perimeter, double area, double volume) {
            this.perimeter = perimeter;
            this.area = area;
            this.volume = volume;
        }

        /**
         * Gets the perimeter of the Shape.
         *
         * @return the perimeter
         */
        public double getPerimeter() {
            return perimeter;
        }

        /**
         * Gets the area of the Shape.
         *
         * @return the area
         */
        public double getArea() {
            return area;
        }

        /**
         * Gets the volume of the Shape.
         *
         * @return the volume
         */
        public double getVolume() {
            return volume;
        }

        /**
         * Returns a string representation of the ShapeParameters.
         *
         * @return a string representation of the parameters
         */
        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder("ShapeParameters{");
            sb.append("perimeter=").append(perimeter)
                    .append(", area=").append(area)
                    .append(", volume=").append(volume)
                    .append('}');
            return sb.toString();
        }
    }
}
