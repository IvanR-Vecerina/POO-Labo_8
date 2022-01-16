package engine.moves;

import engine.Board;
import engine.BoardPos2D;
import engine.pieces.Piece;

public class EnPassant extends Attack {

    public EnPassant(Board board, Piece pieceToMove, BoardPos2D destination, Piece attackedPiece) {
        super(board, pieceToMove, destination, attackedPiece);
    }

    @Override
    public void execute() {
        super.execute();
        board.removeEnPassantPawn();
        board.removeEnPassant();
    }
}
