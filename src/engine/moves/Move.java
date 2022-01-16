package engine.moves;

import engine.Board;
import engine.BoardPos2D;
import engine.pieces.Piece;

public class Move {
    protected final Board board;
    protected final Piece pieceToMove;
    protected final BoardPos2D destination;

    public Move(Board board, Piece pieceToMove, BoardPos2D destination) {
        this.board = board;
        this.pieceToMove = pieceToMove;
        this.destination = destination;
    }

    public void execute() {
        board.movePiece(pieceToMove.getPosition(), destination);
        pieceToMove.setPiecePosition(destination);
    }

    public BoardPos2D getDestination() {
        return destination;
    }
}
