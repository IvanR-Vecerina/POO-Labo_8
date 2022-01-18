package engine.moves;

import engine.AltChessGame;
import engine.BoardPos2D;
import engine.pieces.Piece;

public class Move {
    protected final AltChessGame gameState;
    protected final Piece pieceToMove;
    protected final BoardPos2D destination;

    public Move(AltChessGame gameState, Piece pieceToMove, BoardPos2D destination) {
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
