package lt.esdc.shape.util.action;

import lt.esdc.shape.util.entity.Vector3D;

public class VectorUtil {

    public static Vector3D cross(Vector3D v1, Vector3D v2) {
        return new Vector3D(
                v1.y * v2.z - v1.z * v2.y,
                v1.z * v2.x - v1.x * v2.z,
                v1.x * v2.y - v1.y * v2.x
        );
    }

    public static double dot(Vector3D v1, Vector3D v2) {
        return v1.x * v2.x + v1.y * v2.y + v1.z * v2.z;
    }

    public static double magnitude(Vector3D v) {
        return Math.sqrt(v.x * v.x + v.y * v.y + v.z * v.z);
    }
}

