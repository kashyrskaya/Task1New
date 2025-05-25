package lt.esdc.shape.reader;

import lt.esdc.shape.entity.AbstractShape;
import lt.esdc.shape.entity.Tetrahedron;
import lt.esdc.shape.exception.FileReadException;
import lt.esdc.shape.factory.AbstractShapeFactory;
import lt.esdc.shape.exception.ShapeValidationException;
import lt.esdc.shape.validator.TetrahedronValidatorImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * Class responsible for reading shape coordinates from a file and validating them.
 * It uses a ShapeFactory to create Shape instances and a TetrahedronValidatorImpl to validate Tetrahedrons.
 */

public class ShapeCoordinateReader {
    private static final Logger logger = LogManager.getLogger(ShapeCoordinateReader.class);
    private static final TetrahedronValidatorImpl validator = new TetrahedronValidatorImpl();

    public List<AbstractShape> readShapesFromFile(String filePath, AbstractShapeFactory factory) throws FileReadException {
        List<AbstractShape> shapes = new ArrayList<>();

        try (Stream<String> lines = Files.lines(Paths.get(filePath))) {
            List<String> filteredLines = lines
                    .filter(line -> !line.trim().isEmpty())
                    .toList();

            for (String line : filteredLines) {
                try {
                    String[] parameters = line.split("\\s+");
                    AbstractShape abstractShape = factory.createShape(parameters);
                    if (validator.isValid((Tetrahedron) abstractShape)){
                        shapes.add(abstractShape);
                        logger.info("Shape created: {}", abstractShape.toString());
                    } else {
                        logger.error("Invalid shape data: {}", line);
                        throw new ShapeValidationException("Invalid shape data: " + line);
                    }
                } catch (ShapeValidationException e) {
                    logger.error("Invalid shape data: {}{}", line, e.getMessage());
                }
            }
            return shapes;
        } catch (IOException e) {
            logger.error("Error reading shapes from file: {}{}", filePath, e.getMessage());
            throw new FileReadException("Error reading shapes from file: " + filePath, e);
        }
    }
}
