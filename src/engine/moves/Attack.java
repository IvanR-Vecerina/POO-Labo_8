package engine.moves;

import engine.Board;
import engine.BoardPos2D;
import engine.pieces.Piece;

public class Attack extends Move{
    Piece m_attackedPiece;

    public Attack(Board board, Piece pieceToMove, BoardPos2D destination, Piece attackedPiece) {
        super(board, pieceToMove, destination);
        m_attackedPiece = attackedPiece;
    }

    @Override
    public void execute() {
        board.killPiece(m_attackedPiece);
        super.execute();
    }
}
