package engine.pieces;

import chess.PlayerColor;
import engine.BoardPos2D;
import engine.Game;
import engine.moves.Attack;
import engine.moves.Move;

/**
 * Classe abstraite SlidingPiece, qui hérite de la classe Piece. Cette classe ajoute une sauvegarde permettant de
 * savoir si la pièce a déjà été déplacée ou non (pour le roque et le déplacement de 2 du pion)
 *
 * @author Ivan Vecerina
 * @author (Alice Grunder)
 */
public abstract class SlidingPiece extends Piece{

    /**
     * Constructeur du des pièce glissantes
     * @param piecePosition Position ou le fou est placé
     * @param pieceColour Couleur de la pièce
     */
    SlidingPiece(BoardPos2D piecePosition, PlayerColor pieceColour) {
        super(piecePosition, pieceColour);
    }

    /**
     * Méthode permettant de vérifier qu'aucune pièce ne se trouve sur le chemin de la pièce
     * lorsqu'elle essaie de se déplacer
     * @param gameState état du reste du jeu
     * @param startPosition position de départ de la pièce
     * @param endPosition position d'arrivée de la pièce
     * @param dx déplacement en x de la pièce
     * @param dy déplacement en y de la pièce
     * @return true si aucune pièce ne se trouve sur le chemin de la pièce
     *         false si une pièce se trouve sur le chemin de la pièce
     */
    private static boolean checkMovePath(Game gameState, BoardPos2D startPosition, BoardPos2D endPosition, int dx, int dy){
        BoardPos2D tmpPosition = startPosition;

        int[] vector = { dx == 0 ? 0 : dx/Math.abs(dx), dy == 0 ? 0 : dy/Math.abs(dy)};

        tmpPosition = tmpPosition.offsetBy(vector);

        while (!tmpPosition.equals(endPosition)) {

            if (gameState.getPieceOn(tmpPosition) != null) {
                return false;
            }

            tmpPosition = tmpPosition.offsetBy(vector);
        }

        return true;
    }

    /**
     * Méthode permettant de choisir le type de mouvement qui va être effectué par la pièce
     * @param gameState état du reste du jeu
     * @param destination position d'arrivée de la pièce
     * @param dx déplacement en x de la pièce
     * @param dy déplacement en y de la pièce
     * @return le movement choisi pour la pièce
     *          Move si la pièce bouge sans manger de pièce
     *          Attack si la pièce mange une pièce
     */
    protected Move pickMoveType(Game gameState, BoardPos2D destination, int dx, int dy) {
        if (checkMovePath(gameState, m_piecePosition, destination, dx, dy)) {
            Piece tmp = gameState.getPieceOn(destination);

            if (tmp == null) {
                return new Move(gameState, this, destination);
            } else if (tmp.getColor() != m_pieceColour) {
                return new Attack(gameState, this, destination, tmp);
            }
        }
        return null;
    }
}
