package lt.esdc.shape.loader.impl;

import lt.esdc.shape.entity.Tetrahedron;
import lt.esdc.shape.exception.FileReadException;
import lt.esdc.shape.exception.ShapeValidationException;
import lt.esdc.shape.factory.TetrahedronFactory;
import lt.esdc.shape.parser.CoordinateParser;
import lt.esdc.shape.loader.ShapeLoader;
import lt.esdc.shape.reader.ShapeCoordinateReader;
import lt.esdc.shape.repository.TetrahedronRepository;
import lt.esdc.shape.validator.TetrahedronValidatorImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TetrahedronLoader implements ShapeLoader<Tetrahedron> {
    private static final Logger logger = LogManager.getLogger(TetrahedronLoader.class);
    private final TetrahedronValidatorImpl validator;
    private final TetrahedronFactory factory;
    private final CoordinateParser parser;
    private final ShapeCoordinateReader reader;

    public TetrahedronLoader(TetrahedronFactory factory) {
        this.factory = factory;
        this.validator = new TetrahedronValidatorImpl();
        this.parser = new CoordinateParser();
        this.reader = new ShapeCoordinateReader();
    }

    public List<Tetrahedron> loadShapesFromFile(String filePath) throws ShapeValidationException, FileReadException {
        List<String> lines = reader.readShapesFromFile(filePath);
        if (lines.isEmpty()) {
            logger.warn("No valid lines found in the file: {}", filePath);
            return new ArrayList<>();
        }
        return processLines(lines);
    }

    private List<Tetrahedron> processLines(List<String> lines) throws ShapeValidationException {
        List<Tetrahedron> tetrahedrons = new ArrayList<>();
        TetrahedronRepository repository = TetrahedronRepository.getInstance();

        for (String line : lines) {
            Optional<List<Double>> coordinatesOpt = parser.parseCoordinates(line);
            if (coordinatesOpt.isEmpty()) {
                logger.warn("Skipping invalid line: {}", line);
                continue;
            }

            List<Double> coordinates = coordinatesOpt.get();
            Tetrahedron tetrahedron = factory.createShape(coordinates);
            if (validator.isValid(tetrahedron)) {
                repository.add(tetrahedron);
                tetrahedrons.add(tetrahedron);
                logger.info("Successfully created Tetrahedron: {}", tetrahedron);
            } else {
                logger.warn("Invalid Tetrahedron: {}", tetrahedron);
                throw new ShapeValidationException("Invalid Tetrahedron: " + tetrahedron);
            }
        }

        logger.info("Successfully processed {} valid tetrahedrons out of {} lines", tetrahedrons.size(), lines.size());
        return tetrahedrons;
    }


}
