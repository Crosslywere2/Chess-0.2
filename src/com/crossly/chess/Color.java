package com.crossly.chess;

public enum Color {

    WHITE, BLACK;

    public Color getOpposite() {
        return isWhite() ? BLACK : WHITE;
    }

    public boolean isWhite() {
        return this == WHITE;
    }
}
