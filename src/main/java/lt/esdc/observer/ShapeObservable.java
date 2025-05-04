package lt.esdc.observer;
import lt.esdc.entity.Shape;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class ShapeObservable implements Observable {
    private static final Logger logger = LogManager.getLogger(ShapeObservable.class);

    private final Shape shape;
    private final List<ShapeObserver> observers = new ArrayList<>();

    public ShapeObservable(Shape shape) {
        this.shape = shape;
    }

    @Override
    public void addObserver(ShapeObserver observer) {
        if (!observers.contains(observer)) {
            observers.add(observer);
            logger.debug("Observer added for shape: {}", shape.getId());
        }
    }

    @Override
    public void removeObserver(ShapeObserver observer) {
        if ((observers.remove(observer))) {
            logger.debug("Observer removed from shape: {}", shape.getId());
        }

    }

    @Override
    public void notifyObservers() {
        logger.debug("Notifying {} observers for: {}", observers.size(), shape.getId());
        for (ShapeObserver observer : observers) {
            observer.update(shape);
        }
    }

    @Override
    public List<ShapeObserver> getObservers() {
        return new ArrayList<>(observers);
    }
}
