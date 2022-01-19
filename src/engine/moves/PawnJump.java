package engine.moves;

import engine.Game;
import engine.BoardPos2D;
import engine.pieces.Piece;

public class PawnJump extends Move {
    private final int direction;

    public PawnJump(Game gameState, Piece pieceToMove, BoardPos2D destination, int pawnDirection) {
        super(gameState, pieceToMove, destination);
        direction = pawnDirection;
    }

    @Override
    public void execute() {
        gameState.setPositionEnPassant(pieceToMove.getPosition().offsetBy(new int[]{0, direction}));
        super.execute();
    }
}
