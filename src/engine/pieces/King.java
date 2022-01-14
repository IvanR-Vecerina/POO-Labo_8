package engine.pieces;

import chess.PieceType;
import chess.PlayerColor;
import engine.Board;
import engine.BoardPos2D;
import engine.Move;

import java.util.List;

public class King extends Piece{

    public King(BoardPos2D piecePosition, PlayerColor pieceColour) {
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
        return PieceType.KING;
    }
}
