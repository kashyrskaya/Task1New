package lt.esdc.shape.warehouse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

public class Warehouse {
    private static final Logger logger = LogManager.getLogger(Warehouse.class);
    private static Warehouse instance;
    private final Map<String, ShapeParameters> parametersMap = new HashMap<>();

    private Warehouse() {}

    public static Warehouse getInstance() {
        if (instance == null) {
            instance = new Warehouse();
        }
        return instance;
    }

    public void putParameters(String shapeId, double perimeter, double area, double volume) {
        ShapeParameters parameters = new ShapeParameters(perimeter, area, volume);
        parametersMap.put(shapeId, parameters);
        logger.info("Parameters for shape {} saved: {}", shapeId, parameters);
    }

    public ShapeParameters getParameters(String shapeId) {
        return parametersMap.get(shapeId);
    }

    public void remove(String shapeId) {
        parametersMap.remove(shapeId);
        logger.debug("Parameters for shape {} removed", shapeId);
    }

        public record ShapeParameters(double perimeter, double area, double volume) {

            @Override
            public String toString() {
                StringBuilder sb = new StringBuilder();
                sb.append("ShapeParameters{");
                sb.append("perimeter=").append(perimeter);
                sb.append(", area=").append(area);
                sb.append(", volume=").append(volume);
                sb.append('}');
                return sb.toString();
            }
        }
}
