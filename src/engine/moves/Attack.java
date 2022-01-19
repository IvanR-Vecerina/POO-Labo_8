package engine.moves;

import engine.Game;
import engine.BoardPos2D;
import engine.pieces.Piece;

public class Attack extends Move{
    Piece m_attackedPiece;

    public Attack(Game gameState, Piece pieceToMove, BoardPos2D destination, Piece attackedPiece) {
        super(gameState, pieceToMove, destination);
        m_attackedPiece = attackedPiece;
    }

    @Override
    protected void rollbackMove() {
        super.rollbackMove();
        gameState.movePieceBoard(m_attackedPiece, m_attackedPiece.getPosition());
    }

    @Override
    public void execute() {
        gameState.killPiece(m_attackedPiece);
        super.execute();
    }
}
