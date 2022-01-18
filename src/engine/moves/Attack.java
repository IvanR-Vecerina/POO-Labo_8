package engine.moves;

import engine.AltChessGame;
import engine.BoardPos2D;
import engine.pieces.Piece;

public class Attack extends Move{
    Piece m_attackedPiece;

    public Attack(AltChessGame gameState, Piece pieceToMove, BoardPos2D destination, Piece attackedPiece) {
        super(gameState, pieceToMove, destination);
        m_attackedPiece = attackedPiece;
    }


    @Override
    public void execute() {
        gameState.killPiece(m_attackedPiece);
        super.execute();
    }
}
