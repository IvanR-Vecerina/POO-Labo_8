package engine;

import engine.pieces.Piece;

public class MoveEnPassant extends Move{
    public MoveEnPassant(Board board, Piece pieceToMove, BoardPos2D destination) {
        super(board, pieceToMove, destination);
    }
}
