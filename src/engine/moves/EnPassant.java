package engine.moves;

import engine.Game;
import engine.BoardPos2D;
import engine.pieces.Piece;

/**
 * Classe EnPassant, qui hérite de la classe Attack. Cette classe effectue une prise en passant, tout en vérifiant
 * qu'elle ne mette pas son roi en danger.
 *
 * @author Ivan Vecerina
 * @author Thibault Seem
 */
public class EnPassant extends Attack {

    /**
     * Constructeur d'un move PawnJump
     * @param gameState état actuel du jeu
     * @param pieceToMove pièce qui doit être déplacée
     * @param destination position ou la pièce doit être déplacée
     * @param attackedPiece pièce attaquée
     */
    public EnPassant(Game gameState, Piece pieceToMove, BoardPos2D destination, Piece attackedPiece) {
        super(gameState, pieceToMove, destination, attackedPiece);
    }

    /**
     * Méthode utilisée pour appeler l'affichage des pièces et vérifier que les différentes valeurs liées au premier
     * mouvement d'une pièce soit bien modifiée après un déplacement.
     */
    @Override
    public void execute() {
        super.execute();
        gameState.removeEnPassantPawn();
    }
}
