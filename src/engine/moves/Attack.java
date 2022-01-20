package engine.moves;

import engine.Game;
import engine.BoardPos2D;
import engine.pieces.Piece;

public class Attack extends Move{
    Piece m_attackedPiece;

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
     * Méthode utilisée pour remettre le board dans l'état précédent les test de vérification.
     * Elle est appelée lorsqu'une contrainte est violée.
     */
    @Override
    protected void rollbackMove() {
        super.rollbackMove();
        gameState.movePieceBoard(m_attackedPiece, m_attackedPiece.getPosition());
    }

    /**
     * Méthode utilisée pour appeler l'affichage des pièces et vérifier que les différentes valeurs liées au premier
     * mouvement d'une pièce soit bien modifiée après un déplacement.
     */
    @Override
    public void execute() {
        gameState.killPiece(m_attackedPiece);
        super.execute();
    }
}
