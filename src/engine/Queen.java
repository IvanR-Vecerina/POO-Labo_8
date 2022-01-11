package engine;

import chess.PieceType;
import chess.PlayerColor;

public class Queen extends Piece{
    public Queen(PlayerColor colour) {
        super(colour, PieceType.QUEEN);
    }

    @Override
    public boolean canMove(int x1, int x2, int y1, int y2) {
        return false;
    }
}
