package engine.pieces;

import chess.PlayerColor;
import engine.BoardPos2D;

public abstract class MoveTrackedPiece extends Piece{
    protected boolean hasMoved;

    MoveTrackedPiece(BoardPos2D piecePosition, PlayerColor pieceColour) {
        super(piecePosition, pieceColour);
        hasMoved = false;
    }
}
