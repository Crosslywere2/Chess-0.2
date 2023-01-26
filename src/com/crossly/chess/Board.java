package com.crossly.chess;

import java.util.ArrayList;
import java.util.Arrays;

public class Board {

    public static ArrayList<Piece> whites = new ArrayList<>();

    private static Piece[][] whiteMatrix = new Piece[8][8];

    public static ArrayList<Piece> blacks = new ArrayList<>();

    private static Piece[][] blackMatrix = new Piece[8][8];

    private static boolean whiteChecked = false;

    private static boolean blackChecked = false;

    private static Piece whiteKing, whiteAttacker, blackKing, blackAttacker;

    public static void init() {
        for (int i = 0; i < 8; i++) {
            Arrays.fill(whiteMatrix[i], null);
            Arrays.fill(blackMatrix[i], null);
        }
        for (int i = 0; i < 16; i++) {
            if (i < 8) {
                whites.add(new Piece(Color.WHITE, Type.PAWN, new Vector2(i, 6).mul(Chess.unit)));
                putInWhiteMat(whites.get(i));
                blacks.add(new Piece(Color.BLACK, Type.PAWN, new Vector2(i, 1).mul(Chess.unit)));
                putInBlackMat(blacks.get(i));
            } else if (i == 8 || i == 15) {
                whites.add(new Piece(Color.WHITE, Type.ROOK, new Vector2(i - 8, 7).mul(Chess.unit)));
                putInWhiteMat(whites.get(i));
                blacks.add(new Piece(Color.BLACK, Type.ROOK, new Vector2(i - 8, 0).mul(Chess.unit)));
                putInBlackMat(blacks.get(i));
            } else if (i == 9 || i == 14) {
                whites.add(new Piece(Color.WHITE, Type.KNIGHT, new Vector2(i - 8, 7).mul(Chess.unit)));
                putInWhiteMat(whites.get(i));
                blacks.add(new Piece(Color.BLACK, Type.KNIGHT, new Vector2(i - 8, 0).mul(Chess.unit)));
                putInBlackMat(blacks.get(i));
            } else if (i == 10 || i == 13) {
                whites.add(new Piece(Color.WHITE, Type.BISHOP, new Vector2(i - 8, 7).mul(Chess.unit)));
                putInWhiteMat(whites.get(i));
                blacks.add(new Piece(Color.BLACK, Type.BISHOP, new Vector2(i - 8, 0).mul(Chess.unit)));
                putInBlackMat(blacks.get(i));
            } else if (i == 11) {
                whites.add(new Piece(Color.WHITE, Type.QUEEN, new Vector2(i - 8, 7).mul(Chess.unit)));
                putInWhiteMat(whites.get(i));
                blacks.add(new Piece(Color.BLACK, Type.QUEEN, new Vector2(i - 8, 0).mul(Chess.unit)));
                putInBlackMat(blacks.get(i));
            } else {
                whites.add(new Piece(Color.WHITE, Type.KING, new Vector2(i - 8, 7).mul(Chess.unit)));
                putInWhiteMat(whites.get(i));
                whiteKing = whites.get(i);
                blacks.add(new Piece(Color.BLACK, Type.KING, new Vector2(i - 8, 0).mul(Chess.unit)));
                putInBlackMat(blacks.get(i));
                blackKing = blacks.get(i);
            }
        }
    }

    private static void putInWhiteMat(Piece white) {
        Vector2 hash = white.getPosition().hash();
        whiteMatrix[hash.getX()][hash.getY()] = white;
    }

    private static void nullWhitePosition(Vector2 screenPosition) {
        Vector2 hash = screenPosition.hash();
        if (hash.getX() >= 8 || hash.getY() >= 8) return;
        whiteMatrix[hash.getX()][hash.getY()] = null;
    }

    private static void putInBlackMat(Piece black) {
        Vector2 hash = black.getPosition().hash();
        blackMatrix[hash.getX()][hash.getY()] = black;
    }

    private static void nullBlackPosition(Vector2 screenPosition) {
        Vector2 hash = screenPosition.hash();
        if (hash.getX() >= 8 || hash.getY() >= 8) return;
        blackMatrix[hash.getX()][hash.getY()] = null;
    }

    public static void update(Piece piece) {
        if (piece.isWhite()) {
            nullWhitePosition(piece.getLastPosition());
            putInWhiteMat(piece);
            blackChecked = MoveGenerator.generateMoves(piece).contains(new Move(blackKing.getPosition()));
            if (blackChecked)
                whiteAttacker = piece;
            else
                whiteAttacker = null;
        } else {
            nullBlackPosition(piece.getLastPosition());
            putInBlackMat(piece);
            whiteChecked = MoveGenerator.generateMoves(piece).contains(new Move(whiteKing.getPosition()));
            if (whiteChecked)
                blackAttacker = piece;
            else
                blackAttacker = null;
        }
    }

    public static void addWhite(Piece piece) {
        if (piece == null) return;
        whites.add(piece);
        putInWhiteMat(piece);
    }

    public static void removeWhite(Piece piece) {
        if (piece == null) return;
        nullWhitePosition(piece.getPosition());
        whites.remove(piece);
    }

    public static void addBlack(Piece piece) {
        if (piece == null) return;
        blacks.add(piece);
        putInBlackMat(piece);
    }

    public static void removeBlack(Piece piece) {
        if (piece == null) return;
        nullBlackPosition(piece.getPosition());
        blacks.remove(piece);
    }

    public static Piece getPieceAt(Vector2 screenPosition) {
        Piece p;
        if ((p = getWhitePieceAt(screenPosition)) == null) {
            return getBlackPieceAt(screenPosition);
        }
        return p;
    }

    public static Piece getWhitePieceAt(Vector2 screenPosition) {
        Vector2 hash = screenPosition.hash();
        if (hash.getX() >= 8 || hash.getY() >= 8) return null;
        return whiteMatrix[hash.getX()][hash.getY()];
    }

    public static Piece getBlackPieceAt(Vector2 screenPosition) {
        Vector2 hash = screenPosition.hash();
        if (hash.getX() >= 8 || hash.getY() >= 8) return null;
        return blackMatrix[hash.getX()][hash.getY()];
    }

    public static boolean isPositionValid(Vector2 screenPosition) {
        Vector2 hash = screenPosition.hash();
        return hash.getX() >= 0 && hash.getY() >= 0 && hash.getX() < 8 && hash.getY() < 8;
    }

    public static boolean isWhiteChecked() {
        return whiteChecked;
    }

    public static boolean isBlackChecked() {
        return blackChecked;
    }

    public static Piece getWhiteAttacker() {
        return whiteAttacker;
    }

    public static Piece getBlackAttacker() {
        return blackAttacker;
    }
}
