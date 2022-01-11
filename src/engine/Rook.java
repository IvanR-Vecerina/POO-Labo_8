package engine;

import chess.PieceType;
import chess.PlayerColor;

public class Rook extends Piece{
    public Rook(PlayerColor colour) {
        super(colour, PieceType.ROOK);
    }

    @Override
    public boolean canMove(int fromX, int fromY, int toX, int toY) {
        return true;
    }
}
