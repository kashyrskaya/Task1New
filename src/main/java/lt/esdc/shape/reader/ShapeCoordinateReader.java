package lt.esdc.shape.reader;

import lt.esdc.shape.exception.FileReadException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

/**
 * Class responsible for reading shape coordinates from a file and validating them.
 * It uses a ShapeFactory to create Shape instances and a TetrahedronValidatorImpl to validate Tetrahedrons.
 */

public class ShapeCoordinateReader {
    private static final Logger logger = LogManager.getLogger(ShapeCoordinateReader.class);

    public List<String> readShapesFromFile(String filePath) throws FileReadException {

        try (Stream<String> lines = Files.lines(Paths.get(filePath))) {
            List<String> filteredLines = lines
                    .filter(line -> !line.trim().isEmpty())
                    .toList();

            logger.info("Successfully read {} lines from file: {}", filteredLines.size(), filePath);
            return filteredLines;
        } catch (IOException e) {
            logger.error("Error reading lines from file: {}{}", filePath, e.getMessage());
            throw new FileReadException("Error reading lines from file: " + filePath, e);
        }
    }
}
