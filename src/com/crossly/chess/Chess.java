package com.crossly.chess;

import com.crossly.GameManager;

import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class Chess extends GameManager {

    public static final int unit = 48;

    private Piece selectedPiece = null;

    private Color turn = Color.WHITE;

    private ArrayList<Move> moves = null;

    private double animationTime = 0;

    private boolean log = false;

    public Chess() {
        super("ChessPlusPlus", unit * 8, (unit * 8), 5f / 3);
    }

    @Override
    public void onCreate() {
        Board.init();
    }

    @Override
    public void onUpdate(double delta) {
        Vector2 mouse = new Vector2(input.getMousePos());
        mouse = mouse.sub(mouse.mod(unit));
        if (input.isButtonPressed(MouseEvent.BUTTON1)) {
            if (selectedPiece != null) {
                int moveIndex;
                if ((moveIndex = moves.indexOf(new Move(mouse))) >= 0) {
                    selectedPiece.setPosition(mouse);
                    moves.get(moveIndex).takeAction();
                    if (selectedPiece.isJustMoved()) {
                        Board.update(selectedPiece);
                        turn = turn.getOpposite();
                        if (!turn.isWhite() && Board.isBlackChecked()) {
                            System.out.println("Black King in check");
                        } else if (turn.isWhite() && Board.isWhiteChecked()) {
                            System.out.println("White King is check");
                        }
                    }
                }
                moves = null;
                selectedPiece = null;
            } else {
                if (turn.isWhite()) {
                    selectedPiece = Board.getWhitePieceAt(mouse);
                } else {
                    selectedPiece = Board.getBlackPieceAt(mouse);
                }
                if (selectedPiece != null) {
                    moves = MoveGenerator.generateMoves(selectedPiece);
                    if (log) {
                        System.out.println("Selected " + selectedPiece.getColor() + " " + selectedPiece.getType() + " at " + selectedPiece.getPosition().hash());
                        if (!moves.isEmpty())
                            for (Move move : moves)
                                System.out.println("Move : " + move.getPosition().hash());
                        else
                            System.out.println("No move(s)");
                    }
                }
            }
        }
        if (selectedPiece != null) {
            if (moves.contains(new Move(mouse)))
                selectedPiece.setRenderPosition(mouse);
            else
                selectedPiece.setRenderPosition(selectedPiece.getPosition());
            animationTime += delta * 4;
            animationTime %= 3;
        }
    }

    @Override
    public void onRender() {
        drawBoard();
        if (selectedPiece != null) {
            renderer.fillRectangle(selectedPiece.getPosition(), unit, unit, selectedPiece.isWhite() ? 0xffff4040 : 0xff0080ff);
            for (Move move : moves) {
                drawMoveOption(move.getPosition());
            }
        }
        for (Piece black : Board.blacks) {
            renderer.drawImage(black.getImage(), black.getPosition());
        }
        for (Piece white : Board.whites) {
            renderer.drawImage(white.getImage(), white.getPosition());
        }
        if (selectedPiece != null) {
            renderer.drawImage(selectedPiece.getImage(), selectedPiece.getRenderPosition());
        }
    }

    /**
     * Function that draws the Chess Board by using the
     * {@code Renderer::fillRectangle} function switching
     * color after each rectangle is drawn.
     */
    private void drawBoard() {
        boolean swap = false;
        for (int y = 0; y < 8; y++) {
            swap = !swap;
            for (int x = 0; x < 8; x++) {
                swap = !swap;
                renderer.fillRectangle(x * unit, y * unit, unit, unit, swap ? 0xff6d3e17 : 0xfff0c380);
            }
        }
    }

    /**
     * Function that draws out a box using the
     * {@code Renderer::drawRectangle} called 3 times.
     * The size of the rectangle is determined by
     * {@code animationTime}.
     * @param moveOption A {@code Coordinate} that is the
     *                   position to draw the rectangle at.
     */
    private void drawMoveOption(Vector2 moveOption) {
        int color = turn.isWhite() ? 0xffff4040 : 0xff0080ff;
        renderer.drawRectangle(moveOption.add(3 + (int)(animationTime), 3 + (int)(animationTime)),
                unit - (7 + (int)animationTime * 2), unit - (7 + (int)animationTime * 2),
                color);
        renderer.drawRectangle(moveOption.add(4 + (int)(animationTime), 4 + (int)(animationTime)),
                unit - (9 + (int)animationTime * 2),unit - (9 + (int)animationTime * 2),
                color);
        renderer.drawRectangle(moveOption.add(5 + (int)(animationTime), 5 + (int)(animationTime)),
                unit - (11 + (int)animationTime * 2), unit - (11 + (int)animationTime * 2),
                color);
    }
}
