package engine;

import chess.PieceType;
import chess.PlayerColor;

public class Bishop extends Piece{
    public Bishop(PlayerColor colour) {
        super(colour, PieceType.BISHOP);
    }

    @Override
    public boolean canMove(int x1, int x2, int y1, int y2) {
        return false;
    }
}
