package com.example.action;

import com.example.entity.Shape;

//TODO: not sure if needed, may need to revise
public abstract class ShapeCalculator<T extends Shape> {

    /** For Tetrahedron and other 3D-shapes would compute area of one surface
    **/
    public abstract double computeArea(T shape);

    public abstract double computePerimeter(T shape);
}
