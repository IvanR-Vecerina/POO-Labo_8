package engine.pieces;

import chess.PlayerColor;
import engine.BoardPos2D;

public abstract class MoveTrackedPiece extends Piece{
    protected boolean hasMoved;

    /**
     * constructeur des pièces qui ont des mouvements particulier si elle n'a jamais bougé.
     * @param piecePosition Position de la pièce
     * @param pieceColour Couleur de la pièce
     */
    MoveTrackedPiece(BoardPos2D piecePosition, PlayerColor pieceColour) {
        super(piecePosition, pieceColour);
        hasMoved = false;
    }
}
