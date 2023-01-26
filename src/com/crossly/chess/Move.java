package com.crossly.chess;

import java.util.Objects;

public class Move {

    public interface Secondary {
        void action(Piece piece);
    }

    public static final void take(Piece piece) {
        if (piece.isWhite()) {
            Board.removeWhite(piece);
        } else {
            Board.removeBlack(piece);
        }
    }

    private final Vector2 position;

    private final Secondary secondaryAction;

    private final Piece involvedPiece;

    public Move(Vector2 position) {
        this.position = position;
        this.secondaryAction = (Piece piece) -> {};
        this.involvedPiece = null;
    }

    public Move(Vector2 position, Piece involvedPiece, Secondary secondaryAction) {
        this.position = position;
        this.involvedPiece = involvedPiece;
        this.secondaryAction = secondaryAction;
    }

    public void takeAction() {
        secondaryAction.action(involvedPiece);
    }

    public Vector2 getPosition() {
        return position;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Move move = (Move) o;
        return Objects.equals(getPosition(), move.getPosition());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPosition());
    }
}
