package engine;

import chess.PieceType;
import chess.PlayerColor;

public class Bishop extends Piece{
    public Bishop(PlayerColor colour) {
        super(colour, PieceType.BISHOP);
    }

    @Override
    public boolean canMove(int fromX, int fromY, int toX, int toY) {
        return true;
    }
}
