package engine;

import chess.PieceType;
import chess.PlayerColor;

import java.util.List;

public class Bishop extends Piece{
    Bishop(PieceType pieceType, BoardPos2D piecePosition, PlayerColor pieceColour) {
        super(PieceType.BISHOP, piecePosition, pieceColour);
    }

    @Override
    public Move isPieceLegalMove(Board board, BoardPos2D destination) {
        return null;
    }

    @Override
    public List<Move> calculateLegalMoves(Board board) {
        return null;
    }
}
