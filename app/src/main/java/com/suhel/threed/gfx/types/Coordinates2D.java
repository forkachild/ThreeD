package com.suhel.threed.gfx.types;

public abstract class Coordinates2D {

    protected float[] coordinates;

    public Coordinates2D(float x, float y) {
        this.coordinates = new float[]{x, y};
    }

    public void setCoordinates(float x, float y) {
        this.coordinates[0] = x;
        this.coordinates[1] = y;
    }

    public float getX() {
        return coordinates[0];
    }

    public float getY() {
        return coordinates[1];
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
                    && vector.coordinates[1] == this.coordinates[1]);
        }
        return false;
    }

}
