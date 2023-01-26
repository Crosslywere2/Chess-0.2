package com.crossly.chess;

public enum Type {
    PAWN("/pawn.png", "/pawn1.png"),
    ROOK("/rook.png", "/rook1.png"),
    KNIGHT("/knight.png", "/knight1.png"),
    BISHOP("/bishop.png", "/bishop1.png"),
    QUEEN("/queen.png", "/queen1.png"),
    KING("/king.png", "/king1.png");

    public final String whiteImagePath, blackImagePath;
    Type(String whiteImagePath, String blackImagePath) {
        this.blackImagePath = blackImagePath;
        this.whiteImagePath = whiteImagePath;
    }
}
