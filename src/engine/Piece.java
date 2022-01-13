package engine;

import java.util.List;

public abstract class Piece {
    protected final BoardPos2D m_piecePosition;
    protected final TeamColour m_pieceColour;
    protected final PieceType  m_pieceType;

    // check hasMoved location
    protected boolean hasMoved;

    Piece(final PieceType pieceType,final BoardPos2D piecePosition, final TeamColour pieceColour) {
        m_piecePosition = piecePosition;
        m_pieceColour   = pieceColour;
        m_pieceType     = pieceType;
        hasMoved = false;
    }

    public abstract Move isPieceLegalMove(Board board, BoardPos2D destination);

    public abstract List<Move> calculateLegalMoves(final Board board);

    // Getters
    // Get next turn magic moves
    // Get next turn legal moves


}
