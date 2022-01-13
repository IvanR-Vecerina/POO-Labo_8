package engine;

import chess.PieceType;
import chess.PlayerColor;

import java.util.List;

public class King extends Piece{

    public King(PieceType pieceType, BoardPos2D piecePosition, PlayerColor pieceColour) {
        super(pieceType, piecePosition, pieceColour);
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
