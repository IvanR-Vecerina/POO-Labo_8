package engine;

import chess.PieceType;
import chess.PlayerColor;

import java.util.List;

public class Queen extends Piece{

    Queen(BoardPos2D piecePosition, PlayerColor pieceColour) {
        super(piecePosition, pieceColour);
    }

    @Override
    public Move isPieceLegalMove(Board board, BoardPos2D destination) {
        return null;
    }

    @Override
    public List<Move> calculateLegalMoves(Board board) {
        return null;
    }

    @Override
    public PieceType getPieceName() {
        return PieceType.QUEEN;
    }
}
