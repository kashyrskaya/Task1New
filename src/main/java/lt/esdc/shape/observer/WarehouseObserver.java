package lt.esdc.shape.observer;

import lt.esdc.shape.action.CalculatorFactory;
import lt.esdc.shape.action.ShapeCalculator;
import lt.esdc.shape.entity.AbstractShape;
import lt.esdc.shape.warehouse.Warehouse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Observer that updates the Warehouse with shape parameters when a shape is updated.
 * Implements the ShapeObserver interface to receive notifications about shape changes.
 */

public class WarehouseObserver implements ShapeObserver {
    private static final Logger logger = LogManager.getLogger(WarehouseObserver.class);
    private final Warehouse warehouse = Warehouse.getInstance();

    @Override
    public void update(AbstractShape abstractShape) {
        ShapeCalculator calculator = CalculatorFactory.getCalculator(abstractShape);
        warehouse.putParameters(abstractShape.getId(), calculator.computePerimeter(abstractShape), calculator.computeArea(abstractShape), calculator.computeVolume(abstractShape));
        logger.info("Warehouse updated shape {}", abstractShape);
    }
}
