package lt.esdc.shape.observer.impl;

import lt.esdc.shape.action.CalculatorFactory;
import lt.esdc.shape.action.ShapeCalculator;
import lt.esdc.shape.entity.AbstractShape;
import lt.esdc.shape.observer.ShapeObserver;
import lt.esdc.shape.warehouse.Warehouse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Observer that updates the Warehouse with shape parameters when a shape is updated.
 * Implements the ShapeObserver interface to receive notifications about shape changes.
 */

public class WarehouseObserver implements ShapeObserver {
    private static final Logger logger = LogManager.getLogger(WarehouseObserver.class);

    @Override
    public void update(AbstractShape abstractShape) {
        Warehouse warehouse = Warehouse.getInstance();
        ShapeCalculator calculator = CalculatorFactory.getCalculator(abstractShape);
        String shapeId = abstractShape.getId();
        double shapePerimeter = calculator.computePerimeter(abstractShape);
        double shapeArea = calculator.computeArea(abstractShape);
        double shapeVolume = calculator.computeVolume(abstractShape);
        warehouse.putParameters(shapeId, shapePerimeter, shapeArea, shapeVolume);
        logger.info("Warehouse updated shape {}", abstractShape);
    }
}
