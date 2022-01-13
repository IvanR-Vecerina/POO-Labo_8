package engine;

public class Move {
    protected final Board      board;
    protected final Piece      pieceToMove;
    protected final BoardPos2D destination;

    public Move(Board board, Piece pieceToMove, BoardPos2D destination) {
        this.board = board;
        this.pieceToMove = pieceToMove;
        this.destination = destination;
    }

    public void execute(Board board, Piece pieceToMove, BoardPos2D destination) {

    }

    public BoardPos2D getDestination() {
        return destination;
    }
}
