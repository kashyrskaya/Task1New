package lt.esdc.entity;

/**
 * Represents a Tetrahedron defined by four points in 3D space.
 */
public class Tetrahedron extends Shape {
    private Point pointA;
    private Point pointB;
    private Point pointC;
    private Point pointD;

    /**
     * Constructs a Tetrahedron with the specified points and unique identifier.
     *
     * @param id the unique identifier for the Tetrahedron
     * @param pointA the first point of the Tetrahedron
     * @param pointB the second point of the Tetrahedron
     * @param pointC the third point of the Tetrahedron
     * @param pointD the fourth point of the Tetrahedron
     */
    public Tetrahedron(String id, Point pointA, Point pointB, Point pointC, Point pointD) {
        super(id);
        this.pointA = pointA;
        this.pointB = pointB;
        this.pointC = pointC;
        this.pointD = pointD;
    }

    /**
     * Gets the first point of the Tetrahedron.
     *
     * @return the first point
     */
    public Point getPointA() {
        return pointA;
    }

    /**
     * Sets the first point of the Tetrahedron and notifies observers.
     *
     * @param pointA the new first point
     */
    public void setPointA(Point pointA) {
        this.pointA = pointA;
        notifyObservers();
    }

    /**
     * Gets the second point of the Tetrahedron.
     *
     * @return the second point
     */
    public Point getPointB() {
        return pointB;
    }

    /**
     * Sets the second point of the Tetrahedron and notifies observers.
     *
     * @param pointB the new second point
     */
    public void setPointB(Point pointB) {
        this.pointB = pointB;
        notifyObservers();
    }

    /**
     * Gets the third point of the Tetrahedron.
     *
     * @return the third point
     */
    public Point getPointC() {
        return pointC;
    }

    /**
     * Sets the third point of the Tetrahedron and notifies observers.
     *
     * @param pointC the new third point
     */
    public void setPointC(Point pointC) {
        this.pointC = pointC;
        notifyObservers();
    }

    /**
     * Gets the fourth point of the Tetrahedron.
     *
     * @return the fourth point
     */
    public Point getPointD() {
        return pointD;
    }

    /**
     * Sets the fourth point of the Tetrahedron and notifies observers.
     *
     * @param pointD the new fourth point
     */
    public void setPointD(Point pointD) {
        this.pointD = pointD;
        notifyObservers();
    }

    /**
     * Checks if this Tetrahedron is equal to another object.
     *
     * @param o the object to compare
     * @return true if the objects are equal, false otherwise
     */
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

    /**
     * Computes the hash code for this Tetrahedron.
     *
     * @return the hash code
     */
    @Override
    public int hashCode() {
        int result = pointA.hashCode();
        result = 31 * result + pointB.hashCode();
        result = 31 * result + pointC.hashCode();
        result = 31 * result + pointD.hashCode();
        return result;
    }

    /**
     * Returns a string representation of the Tetrahedron.
     *
     * @return the string representation
     */
    @Override
    public String toString() {
        String sb = "Tetrahedron{" +
                "pointA=" + pointA +
                ", pointB=" + pointB +
                ", pointC=" + pointC +
                ", pointD=" + pointD +
                ", id=" + getId() +
                '}';
        return sb;
    }
}
