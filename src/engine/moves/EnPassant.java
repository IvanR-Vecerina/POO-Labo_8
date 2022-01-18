package engine.moves;

import engine.AltChessGame;
import engine.BoardPos2D;
import engine.pieces.Piece;

public class EnPassant extends Attack {

    public EnPassant(AltChessGame gameState, Piece pieceToMove, BoardPos2D destination, Piece attackedPiece) {
        super(gameState, pieceToMove, destination, attackedPiece);
    }

    @Override
    public void execute() {
        super.execute();
        gameState.removeEnPassantPawn();
    }
}
