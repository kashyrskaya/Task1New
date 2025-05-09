package lt.esdc.reader;

import lt.esdc.entity.Shape;
import lt.esdc.exception.FileReadException;
import lt.esdc.factory.ShapeFactory;
import lt.esdc.exception.ShapeValidationException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class ShapeCoordinateReader {
    private static final Logger logger = LogManager.getLogger(ShapeCoordinateReader.class);

    public List<Shape> readShapesFromFile(String filePath, ShapeFactory factory) throws FileReadException {
        List<Shape> shapes = new ArrayList<>();

        try (Stream<String> lines = Files.lines(Paths.get(filePath))) {
            List<String> filteredLines = lines
                    .filter(line -> !line.trim().isEmpty())
                    .toList();

            for (String line : filteredLines) {
                try {
                    String[] parameters = line.split("\\s+");
                    Shape shape = factory.createShape(parameters);
                    shapes.add(shape);
                    logger.info("Shape created: {}", shape.toString());
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
