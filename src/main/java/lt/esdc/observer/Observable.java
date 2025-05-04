package lt.esdc.observer;

import java.util.List;

public interface Observable {
    void addObserver(ShapeObserver observer);
    void removeObserver(ShapeObserver observer);
    void notifyObservers();
    List<ShapeObserver> getObservers();
}
