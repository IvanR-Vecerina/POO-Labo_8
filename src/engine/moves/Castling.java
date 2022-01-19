package engine.moves;

import engine.Game;
import engine.BoardPos2D;
import engine.pieces.Piece;

public class Castling extends Move {
    Piece m_rookToCastle;

    public Castling(Game gameState, Piece pieceToMove, BoardPos2D destination, Piece rookToCastle) {
        super(gameState, pieceToMove, destination);
        m_rookToCastle = rookToCastle;
    }

    @Override
    public void execute() {
        if (m_rookToCastle.getX() == 7) {
            m_rookToCastle.isPieceLegalMove(gameState, m_rookToCastle.getPosition().offsetBy(new int[]{-2, 0})).execute();
        }
        if (m_rookToCastle.getX() == 0) {
            m_rookToCastle.isPieceLegalMove(gameState, m_rookToCastle.getPosition().offsetBy(new int[]{3, 0})).execute();
        }
        super.execute();
    }
}
