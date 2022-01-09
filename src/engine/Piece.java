package engine;

import java.util.List;

public abstract class Piece {
    protected final int[]    m_piecePosition;
    protected final Alliance m_pieceAlliance;

    Piece(final int[] piecePosition, final Alliance pieceAlliance) {
        m_piecePosition = piecePosition;
        m_pieceAlliance = pieceAlliance;
    }

    public abstract List<int[]> calculateLegalMoves(final Board board);

    // Getters
    // Get next turn magic moves
    // Get next turn legal moves


}
