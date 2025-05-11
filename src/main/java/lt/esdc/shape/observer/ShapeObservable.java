package lt.esdc.shape.observer;

import lt.esdc.shape.entity.Shape;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of the Observable interface for Shape objects.
 * Allows observers to be notified of changes to a specific Shape.
 */
public class ShapeObservable {
    private static final Logger logger = LogManager.getLogger(ShapeObservable.class);

    private final Shape shape;
    private final List<ShapeObserver> observers = new ArrayList<>();

    /**
     * Constructs a ShapeObservable for the specified Shape.
     *
     * @param shape the Shape to observe
     */
    public ShapeObservable(Shape shape) {
        this.shape = shape;
    }


    public void addObserver(ShapeObserver observer) {
        if (!observers.contains(observer)) {
            observers.add(observer);
            logger.debug("Observer added for shape: {}", shape.getId());
        }
    }


    public void removeObserver(ShapeObserver observer) {
        if (observers.remove(observer)) {
            logger.debug("Observer removed from shape: {}", shape.getId());
        }

    }

    public void notifyObservers() {
        logger.debug("Notifying {} observers for: {}", observers.size(), shape.getId());
        for (ShapeObserver observer : observers) {
            observer.update(shape);
        }
    }

    public List<ShapeObserver> getObservers() {
        return new ArrayList<>(observers);
    }
}
