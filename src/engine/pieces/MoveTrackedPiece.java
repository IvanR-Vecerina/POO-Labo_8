package engine.pieces;

import chess.PlayerColor;
import engine.BoardPos2D;

/**
 * Classe abstraite MoveTrackedPiece, qui hérite de la classe Piece. Cette classe ajoute une sauvegarde permettant de
 * savoir si la pièce a déjà été déplacée ou non (pour le roque et le déplacement de 2 du pion)
 *
 * @author Ivan Vecerina
 * @author (Alice Grunder)
 */
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

    /**
     * Méthode permettant de changer l'attribut indiquand si la pièce a bougé pour dire qu'elle a bougé.
     */
    public void hasMoved(){
        hasMoved = true;
    }
}
