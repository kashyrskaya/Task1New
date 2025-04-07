package com.example.validator;

import com.example.entity.Shape;
import com.example.entity.Tetrahedron;

@FunctionalInterface
public interface ShapeValidator<T extends Shape> {

    boolean isValid(T shape);

}
