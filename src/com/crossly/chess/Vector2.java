package com.crossly.chess;

import com.crossly.utils.Coordinate;

public class Vector2 extends Coordinate implements Comparable<Vector2> {

    public Vector2(int x, int y) {
        super(x, y);
    }

    public Vector2(Coordinate coordinate) {
        super(coordinate);
    }

    public Vector2 add(Coordinate c) {
        return new Vector2(getX() + c.getX(), getY() + c.getY());
    }

    public Vector2 add(int x, int y) {
        return new Vector2(getX() + x, getY() + y);
    }

    public Vector2 sub(Coordinate c) {
        return new Vector2(getX() - c.getX(), getY() - c.getY());
    }

    public Vector2 sub(int x, int y) {
        return new Vector2(getX() - x, getY() - y);
    }

    public Vector2 mul(int s) {
        return new Vector2(getX() * s, getY() * s);
    }

    public Vector2 div(int s) {
        return new Vector2(getX() / s, getY() / s);
    }

    public Vector2 mod(int s) {
        return new Vector2(getX() % s, getY() % s);
    }

    public Vector2 hash() {
        return this.sub(this.mod(Chess.unit)).div(Chess.unit);
    }

    public int compareTo(Vector2 v) {
        return Integer.compare(getX(), v.getX()) + (Integer.compare(getY(), v.getY()) * 2);
    }

    public String toString() {
        return String.format("{ %d, %d }", getX(), getY());
    }
}
