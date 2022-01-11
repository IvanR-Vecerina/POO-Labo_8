package engine;

import chess.PieceType;
import chess.PlayerColor;

public class Queen extends Piece{
    public Queen(PlayerColor colour) {
        super(colour, PieceType.QUEEN);
    }

    @Override
    public boolean canMove(int fromX, int fromY, int toX, int toY) {
        return true;
    }
}
