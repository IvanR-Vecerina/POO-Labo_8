package engine.pieces;

import chess.PieceType;
import chess.PlayerColor;
import engine.Game;
import engine.BoardPos2D;
import engine.moves.Move;

import java.util.List;

import static engine.pieces.PieceUtils.*;

public class Bishop extends SlidingPiece{

    private final static int[][] CANDIDATE_MOVE_VECTORS_OFFSETS = {DR, DL, UR, UL};

    /**
     * Constructeur du fou
     * @param piecePosition Position ou le fou est placé
     * @param pieceColour Couleur de la pièce
     */
    public Bishop(BoardPos2D piecePosition, PlayerColor pieceColour) {
        super(piecePosition, pieceColour);
    }

    /**
     * Redéfinition de la méthode isPieceLegalMove de Piece. Cette méthode permet de tester si le move tenté est légal
     * pour le fou.
     * @param gameState état du reste du jeu
     * @param destination position d'arrivée du déplacement tenté
     * @return null si le mouvement n'est pas légal pour le fou,
     *         Attack si le fou mange une pièce
     *         Move si le fou ne fait que se déplacer
     */
    @Override
    public Move isPieceLegalMove(Game gameState, BoardPos2D destination) {
        int deltaX = destination.getX() - m_piecePosition.getX();
        int deltaY = destination.getY() - m_piecePosition.getY();

        if (Math.abs(deltaX) == Math.abs(deltaY)){
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
        return PieceType.BISHOP;
    }

}
