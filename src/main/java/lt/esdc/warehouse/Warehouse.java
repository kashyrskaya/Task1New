package lt.esdc.warehouse;

import lt.esdc.action.ShapeCalculator;
import lt.esdc.entity.Shape;
import lt.esdc.observer.ShapeObserver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

public class Warehouse implements ShapeObserver {
    private static final Logger logger = LogManager.getLogger(Warehouse.class);

    private static Warehouse instance;
    private final Map<String, ShapeParameters> parametersMap;
    private final Map<Class<? extends Shape>, ShapeCalculator> calculatorMap;

    private Warehouse() {
        parametersMap = new HashMap<>();
        calculatorMap = new HashMap<>();
        logger.info("Warehouse created");
    }

    public static Warehouse getInstance() {
        if (instance == null) {
            instance = new Warehouse();
        }
        return instance;
    }

    public void registerCalculator(Class<? extends Shape> shapeClass, ShapeCalculator calculator) {
        calculatorMap.put(shapeClass, calculator);
        logger.debug("Calculator for {} registered in Warehouse", shapeClass.getSimpleName());
    }

    public void putParameters(String shapeId, double perimeter, double area, double volume) {
        ShapeParameters parameters = new ShapeParameters(perimeter, area, volume);
        parametersMap.put(shapeId, parameters);
        logger.debug("Parameters for shape {} saved: {}", shapeId, parameters);
    }

    public ShapeParameters getParameters(String shapeId) {
        ShapeParameters parameters = parametersMap.get(shapeId);
        if (parameters == null) {
            logger.warn("No parameters found for shape {}", shapeId);
        }
        return parameters;
    }

    public boolean containsShape(String shapeId) {
        return parametersMap.containsKey(shapeId);
    }

    public void remove(String shapeId) {
        parametersMap.remove(shapeId);
        logger.debug("Parameters for shape {} removed", shapeId);
    }

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

    public static class ShapeParameters {
        private final double perimeter;
        private final double area;
        private final double volume;

        public ShapeParameters(double perimeter, double area, double volume) {
            this.perimeter = perimeter;
            this.area = area;
            this.volume = volume;
        }

        public double getPerimeter() {
            return perimeter;
        }

        public double getArea() {
            return area;
        }

        public double getVolume() {
            return volume;
        }

        @Override
        public String toString() {
            return "ShapeParameters{" +
                    "perimeter=" + perimeter +
                    ", area=" + area +
                    ", volume=" + volume +
                    '}';
        }
    }
}
