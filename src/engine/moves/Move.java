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

    public void execute() {
        gameState.movePiece(pieceToMove.getPosition(), destination);
        pieceToMove.setPiecePosition(destination);
    }

    public BoardPos2D getDestination() {
        return destination;
    }
}
