package engine.moves;

import engine.Game;
import engine.BoardPos2D;
import engine.pieces.Piece;

public class Move {
    protected final Game gameState;
    protected final Piece pieceToMove;
    protected final BoardPos2D destination;

    public Move(Game gameState, Piece pieceToMove, BoardPos2D destination) {
        this.gameState = gameState;
        this.pieceToMove = pieceToMove;
        this.destination = destination;
    }

    // Apply move to engine (board + pieceData update)
    public void tryMove() {
        // apply move to board and piece(s)

        // Check putting yourself in check?

        // if (check success)
            // move.execute();
        // else
            // rollback
    }

    // Rollback changes on engine
    public void rollbackMove() {

    }

    public void execute() {
        gameState.movePiece(pieceToMove.getPosition(), destination);
        pieceToMove.setPiecePosition(destination);
    }

    public BoardPos2D getDestination() {
        return destination;
    }
}
