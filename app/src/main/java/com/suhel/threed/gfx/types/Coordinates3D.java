package com.suhel.threed.gfx.types;

public abstract class Coordinates3D {

    protected float[] coordinates;

    public Coordinates3D(float x, float y, float z) {
        this.coordinates = new float[]{x, y, z};
    }

    public void setCoordinates(float x, float y, float z) {
        this.coordinates[0] = x;
        this.coordinates[1] = y;
        this.coordinates[2] = z;
    }

    public float getX() {
        return coordinates[0];
    }

    public float getY() {
        return coordinates[1];
    }

    public float getZ() {
        return coordinates[2];
    }

    @Override
    public int hashCode() {
        if (coordinates == null)
            return 0;

        int sum = 0;

        for (float coordinate : coordinates)
            sum += (int) coordinate;

        return sum;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Vector) {
            Vector vector = (Vector) obj;
            return (vector.coordinates[0] == this.coordinates[0]
                    && vector.coordinates[1] == this.coordinates[1]
                    && vector.coordinates[2] == this.coordinates[2]);
        }
        return false;
    }

}
