package engine.moves;

import engine.Board;
import engine.BoardPos2D;
import engine.pieces.Piece;

public class Castling extends Move {
    Piece m_rookToCastle;

    public Castling(Board board, Piece pieceToMove, BoardPos2D destination, Piece rookToCastle) {
        super(board, pieceToMove, destination);
        m_rookToCastle = rookToCastle;
    }

    @Override
    public void execute() {
        if (m_rookToCastle.getX() == 7) {
            m_rookToCastle.isPieceLegalMove(board, m_rookToCastle.getPosition().offsetBy(new int[]{-2, 0})).execute();
        }
        if (m_rookToCastle.getX() == 0) {
            m_rookToCastle.isPieceLegalMove(board, m_rookToCastle.getPosition().offsetBy(new int[]{3, 0})).execute();
        }
        super.execute();
    }
}
