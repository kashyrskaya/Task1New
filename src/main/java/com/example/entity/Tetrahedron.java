package com.example.entity;

public class Tetrahedron extends Shape {
    private final Point pointA;
    private final Point pointB;
    private final Point pointC;
    private final Point pointD;

    public Tetrahedron(String id, Point pointA, Point pointB, Point pointC, Point pointD) {
        super(id);
        this.pointA = pointA;
        this.pointB = pointB;
        this.pointC = pointC;
        this.pointD = pointD;
    }

    public Point getPointA() {
        return pointA;
    }

    public Point getPointB() {
        return pointB;
    }

    public Point getPointC() {
        return pointC;
    }

    public Point getPointD() {
        return pointD;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Tetrahedron tetrahedron = (Tetrahedron) o;

        if (!pointA.equals(tetrahedron.pointA)) return false;
        if (!pointB.equals(tetrahedron.pointB)) return false;
        if (!pointC.equals(tetrahedron.pointC)) return false;
        return pointD.equals(tetrahedron.pointD);
    }

    @Override
    public int hashCode() {
        int result = pointA.hashCode();
        result = 31 * result + pointB.hashCode();
        result = 31 * result + pointC.hashCode();
        result = 31 * result + pointD.hashCode();
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Tetrahedron{");
        sb.append("pointA=").append(pointA)
                .append(", pointB=").append(pointB)
                .append(", pointC=").append(pointC)
                .append(", pointD=").append(pointD)
                .append('}');

        return sb.toString();
    }
}
