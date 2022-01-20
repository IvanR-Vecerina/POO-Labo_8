package engine.moves;

import engine.Game;
import engine.BoardPos2D;
import engine.pieces.Piece;

public class PawnJump extends Move {
    private final int direction;

    /**
     * Constructeur d'un move PawnJump
     * @param gameState état actuel du jeu
     * @param pieceToMove pièce qui doit être déplacée
     * @param destination position ou la pièce doit être déplacée
     * @param pawnDirection direction dans laquelle le pion se déplace
     */
    public PawnJump(Game gameState, Piece pieceToMove, BoardPos2D destination, int pawnDirection) {
        super(gameState, pieceToMove, destination);
        direction = pawnDirection;
    }

    /**
     * Méthode utilisée pour appeler l'affichage des pièces et vérifier que les différentes valeurs liées au premier
     * mouvement d'une pièce soit bien modifiée après un déplacement.
     */
    @Override
    public void execute() {
        gameState.setPositionEnPassant(posInit.offsetBy(new int[]{0, direction}));
        super.execute();
    }
}
