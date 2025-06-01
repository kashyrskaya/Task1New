package lt.esdc.shape.loader;

import lt.esdc.shape.entity.AbstractShape;
import lt.esdc.shape.exception.FileReadException;
import lt.esdc.shape.exception.ShapeValidationException;

import java.util.List;

public interface ShapeLoader<T extends AbstractShape> {

    List<T> loadShapesFromFile(String filePath) throws ShapeValidationException, FileReadException;
}
