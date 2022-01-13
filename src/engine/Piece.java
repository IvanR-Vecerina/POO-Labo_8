package engine;

import chess.PieceType;
import chess.PlayerColor;

import java.util.List;

public abstract class Piece {
    protected final BoardPos2D  m_piecePosition;
    protected final PlayerColor m_pieceColour;

    // check hasMoved location
    protected boolean hasMoved;

    Piece(final BoardPos2D piecePosition, final PlayerColor pieceColour) {
        m_piecePosition = piecePosition;
        m_pieceColour   = pieceColour;
        hasMoved = false;
    }

    public abstract Move isPieceLegalMove(Board board, BoardPos2D destination);

    public abstract List<Move> calculateLegalMoves(final Board board);

    public abstract PieceType getPieceName();

    // Getters
    // Get next turn magic moves
    // Get next turn legal moves


}
