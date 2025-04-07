package com.example.factory;

import com.example.entity.Shape;
import com.example.exception.ShapeValidationException;

public abstract class ShapeFactory {
    public abstract Shape createShape(String[] parameters) throws ShapeValidationException;
}
