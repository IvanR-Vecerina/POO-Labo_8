package engine;

import chess.PieceType;
import chess.PlayerColor;

public class Rook extends Piece{
    public Rook(PlayerColor colour) {
        super(colour, PieceType.ROOK);
    }

    @Override
    public boolean canMove(int x1, int x2, int y1, int y2) {
        return false;
    }
}
