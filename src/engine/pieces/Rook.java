package engine.pieces;

import chess.PieceType;
import chess.PlayerColor;
import engine.Game;
import engine.BoardPos2D;
import engine.moves.Move;

import java.util.List;

import static engine.pieces.PieceUtils.*;
/**
 * Classe Rook, qui hérite de la classe SlidingPiece. Cette classe permet la représentation d'une tour sur le jeu.
 * Elle permet également de tester si le mouvement demandé correspond à un mouvement autorisé par la tour.
 *
 * @author Ivan Vecerina
 * @author (Alice Grunder)
 */
public class Rook extends SlidingPiece{

    private final static int[][] CANDIDATE_MOVE_VECTORS_OFFSETS = {U, D, L, R};

    /**
     * Constructeur de la tour
     * @param piecePosition Position ou la tour est placé
     * @param pieceColour Couleur de la pièce
     */
    public Rook(BoardPos2D piecePosition, PlayerColor pieceColour) {
        super(piecePosition, pieceColour);
    }

    /**
     * Redéfinition de la méthode isPieceLegalMove de Piece. Cette méthode permet de tester si le move tenté est légal
     * pour la tour.
     * @param gameState état du reste du jeu
     * @param destination position d'arrivée du déplacement tenté
     * @return null si le mouvement n'est pas légal pour la tour,
     *         Attack si la tour mange une pièce
     *         Move si la tour ne fait que se déplacer
     */
    @Override
    public Move isPieceLegalMove(Game gameState, BoardPos2D destination) {
        int deltaX = destination.getX() - m_piecePosition.getX();
        int deltaY = destination.getY() - m_piecePosition.getY();

        if (deltaX == 0 || deltaY == 0){
            return pickMoveType(gameState, destination, deltaX, deltaY);
        }
        return null;
    }

    /**
     * Méthode pour savoir de quel type de pièce il s'agit
     * @return le type de pièce
     */
    @Override
    public PieceType getPieceName() {
        return PieceType.ROOK;
    }
}
