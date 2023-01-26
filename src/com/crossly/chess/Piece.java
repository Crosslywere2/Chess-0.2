package com.crossly.chess;

import com.crossly.gfx.Image;

public class Piece {

    private Image image;

    private Color color;

    private Type type;

    private Vector2 position;

    private Vector2 lastPosition;

    private Vector2 renderPosition;

    private boolean moved, justMoved;

    public Piece(Color color, Type type, Vector2 position) {
        this.color = color;
        this.type = type;
        this.position = position;
        this.lastPosition = position;
        this.renderPosition = position;
        image = new Image(color.isWhite() ? type.whiteImagePath : type.blackImagePath);
        moved = justMoved = false;
    }

    public Image getImage() {
        return image;
    }

    public Vector2 getPosition() {
        return position;
    }

    /**
     * Sets the current position of the piece if the position passed into
     * the function is a valid position or isn't its current {@code position} variable. If
     * the above is done it also sets the {@code lastPosition} variable to the current
     * position variable before changing said current position to the passed in
     * position, and sets the {@code justMoved} variable to its appropriate value.
     * <br>
     * If the position is changed, {@code moved} variable is set to true.
     * @param position
     */
    public void setPosition(Vector2 position) {
        if (!Board.isPositionValid(position) || this.position.compareTo(position) == 0) {
            justMoved = false;
            return;
        }
        justMoved = true;
        lastPosition = this.position;
        this.position = position;
        if (!moved && lastPosition.compareTo(position) != 0) moved = true;
    }

    public Vector2 getLastPosition() {
        return lastPosition;
    }

    public Vector2 getRenderPosition() {
        return renderPosition;
    }

    public void setRenderPosition(Vector2 renderPosition) {
        Vector2 hash = renderPosition.hash();
        if (hash.getX() >= 8 || hash.getX() < 0 || hash.getY() >= 8 || hash.getY() < 0) return;
        this.renderPosition = renderPosition;
    }

    public boolean isMoved() {
        return moved;
    }

    public boolean isJustMoved() {
        return justMoved;
    }

    public boolean isWhite() {
        return color.isWhite();
    }

    public Type getType() {
        return type;
    }

    public Color getColor() {
        return color;
    }
}
