package com.crossly.chess;

import java.util.ArrayList;

public class MoveGenerator {

    public static final Vector2 up = new Vector2(0, -Chess.unit);

    public static final Vector2 down = new Vector2(0, Chess.unit);

    public static final Vector2 right = new Vector2(Chess.unit, 0);

    public static final Vector2 left = new Vector2(-Chess.unit, 0);

    public static ArrayList<Move> generateMoves(Piece piece) {
        ArrayList<Move> moves = null;
        switch (piece.getType()) {
            case PAWN -> moves = generatePawnMoves(piece);
            case ROOK -> moves = generateRookMoves(piece);
            case KNIGHT -> moves = generateKnightMoves(piece);
            case BISHOP -> moves = generateBishopMoves(piece);
            case QUEEN -> moves = generateQueenMoves(piece);
            case KING -> moves = generateKingMoves(piece);
        }
        return cullMoves(moves, piece);
    }

    public static ArrayList<Move> generatePawnMoves(Piece pawn) {
        ArrayList<Move> moves = new ArrayList<>(1);
        Vector2 movePos = pawn.getPosition().add(pawn.isWhite() ? up : down);
        if (Board.isPositionValid(movePos)) {
            if (Board.getPieceAt(movePos) == null) {
                moves.add(new Move(movePos));
                movePos = movePos.add(pawn.isWhite() ? up : down);
                if (!pawn.isMoved() && Board.getPieceAt(movePos) == null) {
                    moves.add(new Move(movePos));
                }
            }
        }
        Piece other;
        movePos = pawn.getPosition().add(pawn.isWhite() ? up : down).add(left);
        if (Board.isPositionValid(movePos)) {
            if ((other = Board.getPieceAt(movePos)) != null) {
                if (other.getColor() != pawn.getColor()) {
                    moves.add(new Move(movePos, other, Move::take));
                }
            }
        }
        movePos = pawn.getPosition().add(pawn.isWhite() ? up : down).add(right);
        if (Board.isPositionValid(movePos)) {
            if ((other = Board.getPieceAt(movePos)) != null) {
                if (other.getColor() != pawn.getColor()) {
                    moves.add(new Move(movePos, other, Move::take));
                }
            }
        }
        return moves;
    }

    public static ArrayList<Move> generateRookMoves(Piece rook) {
        ArrayList<Move> moves = new ArrayList<>(1);
        Piece other;
        Vector2 movePos = rook.getPosition();
        for (int i = 0; i < 8; i++) {
            movePos = movePos.add(up);
            if (!Board.isPositionValid(movePos))
                break;
            else {
                if ((other = Board.getPieceAt(movePos)) == null)
                    moves.add(new Move(movePos));
                else if (other.getColor() != rook.getColor()) {
                    moves.add(new Move(movePos, other, Move::take));
                    break;
                } else
                    break;
            }
        }
        movePos = rook.getPosition();
        for (int i = 0; i < 8; i++) {
            movePos = movePos.add(down);
            if (!Board.isPositionValid(movePos))
                break;
            else {
                if ((other = Board.getPieceAt(movePos)) == null)
                    moves.add(new Move(movePos));
                else if (other.getColor() != rook.getColor()) {
                    moves.add(new Move(movePos, other, Move::take));
                    break;
                } else
                    break;
            }
        }
        movePos = rook.getPosition();
        for (int i = 0; i < 8; i++) {
            movePos = movePos.add(left);
            if (!Board.isPositionValid(movePos))
                break;
            else {
                if ((other = Board.getPieceAt(movePos)) == null)
                    moves.add(new Move(movePos));
                else if (other.getColor() != rook.getColor()) {
                    moves.add(new Move(movePos, other, Move::take));
                    break;
                } else
                    break;
            }
        }
        movePos = rook.getPosition();
        for (int i = 0; i < 8; i++) {
            movePos = movePos.add(right);
            if (!Board.isPositionValid(movePos))
                break;
            else {
                if ((other = Board.getPieceAt(movePos)) == null)
                    moves.add(new Move(movePos));
                else if (other.getColor() != rook.getColor()) {
                    moves.add(new Move(movePos, other, Move::take));
                    break;
                } else
                    break;
            }
        }
        return moves;
    }

    public static ArrayList<Move> generateKnightMoves(Piece knight) {
        ArrayList<Move> moves = new ArrayList<>(1);
        Piece other;
        Vector2 movePos = knight.getPosition().add(up).add(up).add(left);
        if (Board.isPositionValid(movePos)) {
            other = Board.getPieceAt(movePos);
            if (other == null)
                moves.add(new Move(movePos));
            else if (other.getColor() != knight.getColor())
                moves.add(new Move(movePos, other, Move::take));
        }
        movePos = knight.getPosition().add(up).add(left).add(left);
        if (Board.isPositionValid(movePos)) {
            other = Board.getPieceAt(movePos);
            if (other == null)
                moves.add(new Move(movePos));
            else if (other.getColor() != knight.getColor())
                moves.add(new Move(movePos, other, Move::take));
        }
        movePos = knight.getPosition().add(up).add(up).add(right);
        if (Board.isPositionValid(movePos)) {
            other = Board.getPieceAt(movePos);
            if (other == null)
                moves.add(new Move(movePos));
            else if (other.getColor() != knight.getColor())
                moves.add(new Move(movePos, other, Move::take));
        }
        movePos = knight.getPosition().add(up).add(right).add(right);
        if (Board.isPositionValid(movePos)) {
            other = Board.getPieceAt(movePos);
            if (other == null)
                moves.add(new Move(movePos));
            else if (other.getColor() != knight.getColor())
                moves.add(new Move(movePos, other, Move::take));
        }
        movePos = knight.getPosition().add(down).add(down).add(left);
        if (Board.isPositionValid(movePos)) {
            other = Board.getPieceAt(movePos);
            if (other == null)
                moves.add(new Move(movePos));
            else if (other.getColor() != knight.getColor())
                moves.add(new Move(movePos, other, Move::take));
        }
        movePos = knight.getPosition().add(down).add(left).add(left);
        if (Board.isPositionValid(movePos)) {
            other = Board.getPieceAt(movePos);
            if (other == null)
                moves.add(new Move(movePos));
            else if (other.getColor() != knight.getColor())
                moves.add(new Move(movePos, other, Move::take));
        }
        movePos = knight.getPosition().add(down).add(down).add(right);
        if (Board.isPositionValid(movePos)) {
            other = Board.getPieceAt(movePos);
            if (other == null)
                moves.add(new Move(movePos));
            else if (other.getColor() != knight.getColor())
                moves.add(new Move(movePos, other, Move::take));
        }
        movePos = knight.getPosition().add(down).add(right).add(right);
        if (Board.isPositionValid(movePos)) {
            other = Board.getPieceAt(movePos);
            if (other == null)
                moves.add(new Move(movePos));
            else if (other.getColor() != knight.getColor())
                moves.add(new Move(movePos, other, Move::take));
        }
        return moves;
    }

    public static ArrayList<Move> generateBishopMoves(Piece bishop) {
        ArrayList<Move> moves = new ArrayList<>(1);
        Piece other;
        Vector2 movePos = bishop.getPosition();
        for (int i = 0; i < 8; i++) {
            movePos = movePos.add(down).add(right);
            if (!Board.isPositionValid(movePos))
                break;
            else {
                if ((other = Board.getPieceAt(movePos)) == null)
                    moves.add(new Move(movePos));
                else if (other.getColor() != bishop.getColor()) {
                    moves.add(new Move(movePos, other, Move::take));
                    break;
                } else
                    break;
            }
        }
        movePos = bishop.getPosition();
        for (int i = 0; i < 8; i++) {
            movePos = movePos.add(down).add(left);
            if (!Board.isPositionValid(movePos))
                break;
            else {
                if ((other = Board.getPieceAt(movePos)) == null)
                    moves.add(new Move(movePos));
                else if (other.getColor() != bishop.getColor()) {
                    moves.add(new Move(movePos, other, Move::take));
                    break;
                } else
                    break;
            }
        }
        movePos = bishop.getPosition();
        for (int i = 0; i < 8; i++) {
            movePos = movePos.add(left).add(up);
            if (!Board.isPositionValid(movePos))
                break;
            else {
                if ((other = Board.getPieceAt(movePos)) == null)
                    moves.add(new Move(movePos));
                else if (other.getColor() != bishop.getColor()) {
                    moves.add(new Move(movePos, other, Move::take));
                    break;
                } else
                    break;
            }
        }
        movePos = bishop.getPosition();
        for (int i = 0; i < 8; i++) {
            movePos = movePos.add(right).add(up);
            if (!Board.isPositionValid(movePos))
                break;
            else {
                if ((other = Board.getPieceAt(movePos)) == null)
                    moves.add(new Move(movePos));
                else if (other.getColor() != bishop.getColor()) {
                    moves.add(new Move(movePos, other, Move::take));
                    break;
                } else
                    break;
            }
        }

        return moves;
    }

    public static ArrayList<Move> generateQueenMoves(Piece queen) {
        ArrayList<Move> moves = new ArrayList<>();
        moves.addAll(generateRookMoves(queen));
        moves.addAll(generateBishopMoves(queen));
        return moves;
    }

    public static ArrayList<Move> generateKingMoves(Piece king) {
        ArrayList<Move> moves = new ArrayList<>(1);
        Piece other;
        Vector2 movePos = king.getPosition().add(up);
        if (Board.isPositionValid(movePos)) {
            if ((other = Board.getPieceAt(movePos)) == null)
                moves.add(new Move(movePos));
            else if (other.getColor() != king.getColor())
                moves.add(new Move(movePos, other, Move::take));
        }
        movePos = king.getPosition().add(up).add(right);
        if (Board.isPositionValid(movePos)) {
            if ((other = Board.getPieceAt(movePos)) == null)
                moves.add(new Move(movePos));
            else if (other.getColor() != king.getColor())
                moves.add(new Move(movePos, other, Move::take));
        }
        movePos = king.getPosition().add(right);
        if (Board.isPositionValid(movePos)) {
            if ((other = Board.getPieceAt(movePos)) == null)
                moves.add(new Move(movePos));
            else if (other.getColor() != king.getColor())
                moves.add(new Move(movePos, other, Move::take));
        }
        movePos = king.getPosition().add(down).add(right);
        if (Board.isPositionValid(movePos)) {
            if ((other = Board.getPieceAt(movePos)) == null)
                moves.add(new Move(movePos));
            else if (other.getColor() != king.getColor())
                moves.add(new Move(movePos, other, Move::take));
        }
        movePos = king.getPosition().add(down);
        if (Board.isPositionValid(movePos)) {
            if ((other = Board.getPieceAt(movePos)) == null)
                moves.add(new Move(movePos));
            else if (other.getColor() != king.getColor())
                moves.add(new Move(movePos, other, Move::take));
        }
        movePos = king.getPosition().add(down).add(left);
        if (Board.isPositionValid(movePos)) {
            if ((other = Board.getPieceAt(movePos)) == null)
                moves.add(new Move(movePos));
            else if (other.getColor() != king.getColor())
                moves.add(new Move(movePos, other, Move::take));
        }
        movePos = king.getPosition().add(left);
        if (Board.isPositionValid(movePos)) {
            if ((other = Board.getPieceAt(movePos)) == null)
                moves.add(new Move(movePos));
            else if (other.getColor() != king.getColor())
                moves.add(new Move(movePos, other, Move::take));
        }
        movePos = king.getPosition().add(up).add(left);
        if (Board.isPositionValid(movePos)) {
            if ((other = Board.getPieceAt(movePos)) == null)
                moves.add(new Move(movePos));
            else if (other.getColor() != king.getColor())
                moves.add(new Move(movePos, other, Move::take));
        }
        if (!king.isMoved()) {
            // Left
            movePos = new Vector2(0, king.getPosition().getY());
            if ((other = Board.getPieceAt(movePos)) != null && Board.getPieceAt(movePos.sub(left)) == null
                    && Board.getPieceAt(movePos.sub(left).sub(left)) == null && Board.getPieceAt(movePos.sub(left).sub(left)) == null) {
                if (other.getColor() == king.getColor() && other.getType() == Type.ROOK && !other.isMoved()) {
                    moves.add(new Move(king.getPosition().add(left).add(left), other, (Piece piece) -> {
                        piece.setPosition(king.getPosition().add(right));
                        Board.update(piece);
                    }));
                }
            }
            // Right
            movePos = new Vector2(7 * Chess.unit, king.getPosition().getY());
            if ((other = Board.getPieceAt(movePos)) != null && Board.getPieceAt(movePos.sub(right)) == null
                    && Board.getPieceAt(movePos.sub(right).sub(right)) == null) {
                if (other.getColor() == king.getColor() && other.getType() == Type.ROOK && !other.isMoved()) {
                    moves.add(new Move(king.getPosition().add(right).add(right), other, (Piece piece) -> {
                        piece.setPosition(king.getPosition().add(left));
                        Board.update(piece);
                    }));
                }
            }
        }
        return moves;
    }

    /**
     * Removes any invalid move eg moves that put the king in check
     * or moves that don't remove the king from check.
     * @param moves an ArrayList of moves to be culled.
     * @param piece the piece getting its moves culled.
     * @return an ArrayList of culled moves
     */
    private static ArrayList<Move> cullMoves(ArrayList<Move> moves, Piece piece) {
        ArrayList<Move> result = new ArrayList<>();
        if (piece.getType() != Type.KING) {
            System.out.println("Culling moves for " + piece.getType());
            if (Board.isWhiteChecked() && piece.isWhite()) {
                System.out.println("Remove all none King saving moves");
                // TODO Remove any move that doesn't uncheck the king
                for (Move move : moves) {
                    if (move.getPosition().compareTo(Board.getBlackAttacker().getPosition()) == 0)
                        result.add(move);
                }
            } else if (Board.isBlackChecked() && !piece.isWhite()) {
                // TODO Remove any move that doesn't uncheck the king
                for (Move move : moves) {
                    if (move.getPosition().compareTo(Board.getWhiteAttacker().getPosition()) == 0)
                        result.add(move);
                }
            }
        } else {

        }
        moves.removeAll(result);
        return moves;
    }
}
