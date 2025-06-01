package lt.esdc.shape.parser;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CoordinateParser {
    private static final Logger logger = LogManager.getLogger(CoordinateParser.class);
    private static final String COORDINATES_SEPARATOR = "\\s+";

    public Optional<List<Double>> parseCoordinates(String line) {
        if (line == null || line.trim().isEmpty()) {
           return Optional.empty();
        }

        try {
            String[] tokens = line.trim().split(COORDINATES_SEPARATOR);
            if (tokens.length != 12) {
                return Optional.empty();
            }

            List<Double> coordinates = new ArrayList<>();

            for (String token : tokens) {
                coordinates.add(Double.parseDouble(token));
            }

            logger.debug("Parsed {} coordinates from line: {}", coordinates.size(), line);
            return Optional.of(coordinates);
        } catch (NumberFormatException e) {
            logger.error("Invalid number format in line '{}': {}", line, e.getMessage());
            return Optional.empty();
        }
    }
}
