package engine.moves;

import engine.Game;
import engine.BoardPos2D;
import engine.pieces.Piece;

/**
 * Classe Attack, qui hérite de Move. Cette classe est utilisée lorsqu'une pièce en mange une autre. Elle permet de
 * tester si le mouvement ne met pas le roi en échec, puis de supprimer la pièce du plateau et du joueur.
 *
 * @author Ivan Vecerina
 * @author Thibault Seem
 */
public class Attack extends Move{
    Piece m_attackedPiece;
    Piece copiPiece;

    /**
     * Constructeur d'un move PawnJump
     * @param gameState état actuel du jeu
     * @param pieceToMove pièce qui doit être déplacée
     * @param destination position ou la pièce doit être déplacée
     * @param attackedPiece pièce attaquée
     */
    public Attack(Game gameState, Piece pieceToMove, BoardPos2D destination, Piece attackedPiece) {
        super(gameState, pieceToMove, destination);
        m_attackedPiece = attackedPiece;
    }
    /**
     * Méthode utilisée pour essayer de déplacer la pièce à son point d'arrivée. Nous sauvegardons sa position
     * de départ au cas ou le mouvement ne respecte pas les contraintes du moves.
     */
    protected void tryMove(){

        copiPiece = m_attackedPiece;
        gameState.killPiece(m_attackedPiece);

        super.tryMove();
    }

    /**
     * Méthode utilisée pour remettre le board dans l'état précédent les test de vérification.
     * Elle est appelée lorsqu'une contrainte est violée.
     */
    @Override
    protected void rollbackMove() {
        super.rollbackMove();
        gameState.movePieceBoard(m_attackedPiece, m_attackedPiece.getPosition());
        gameState.getCurrentPayer().addPiece(copiPiece);
    }

    /**
     * Méthode utilisée pour appeler l'affichage des pièces et vérifier que les différentes valeurs liées au premier
     * mouvement d'une pièce soit bien modifiée après un déplacement.
     */
    @Override
    public void execute() {
        gameState.getCurrentPayer().addPiece(copiPiece);
        gameState.killPiece(m_attackedPiece);
        super.execute();
    }
}
