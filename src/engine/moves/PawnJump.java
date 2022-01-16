package engine.moves;

import engine.Board;
import engine.BoardPos2D;
import engine.pieces.Piece;

public class PawnJump extends Move {
    private final int direction;

    public PawnJump(Board board, Piece pieceToMove, BoardPos2D destination, int pawnDirection) {
        super(board, pieceToMove, destination);
        direction = pawnDirection;
    }

    @Override
    public void execute() {
        super.execute();
        board.setPositionEnPassant(pieceToMove.getPosition().offsetBy(new int[]{0, direction}));
    }
}
