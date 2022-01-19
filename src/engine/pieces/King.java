package engine.pieces;

import chess.PieceType;
import chess.PlayerColor;
import engine.Game;
import engine.BoardPos2D;
import engine.moves.Attack;
import engine.moves.Castling;
import engine.moves.Move;

import java.util.List;

public class King extends MoveTrackedPiece {

    private final static int[][] CASTLING_MOVES_OFFSETS = {{-2, 0}, {2, 0}};

    /**
     * Constructeur du roi
     * @param piecePosition Position ou le roi est placé
     * @param pieceColour Couleur de la pièce
     */
    public King(BoardPos2D piecePosition, PlayerColor pieceColour) {
        super(piecePosition, pieceColour);
    }

    /**
     * Redéfinition de la méthode isPieceLegalMove de Piece. Cette méthode permet de tester si le move tenté est légal
     * pour le fou. Elle permet également de tester si on a déjà fait le rock ou non.
     * @param gameState état du reste du jeu
     * @param destination position d'arrivée du déplacement tenté
     * @return null si le mouvement n'est pas légal pour le roi,
     *         Attack si le roi mange une pièce
     *         Move si le roi ne fait que se déplacer
     *         casling si le roi fait un rock
     */
    @Override
    public Move isPieceLegalMove(Game gameState, BoardPos2D destination) {
        Piece pieceOnDestination = gameState.getPieceOn(destination);
        int deltaX = destination.getX() - m_piecePosition.getX();
        int deltaY = destination.getY() - m_piecePosition.getY();

        if (Math.abs(deltaX + deltaY) == 1 || Math.abs(deltaX * deltaY) == 1){

            if (pieceOnDestination == null) {
                return new Move(gameState, this, destination);
            } else if (pieceOnDestination.getColor() != m_pieceColour) {
                return new Attack(gameState, this, destination, pieceOnDestination);
            }
        }

        if (!hasMoved) {
            if (pieceOnDestination == null && destination.equals(m_piecePosition.offsetBy(CASTLING_MOVES_OFFSETS[0])))
                return new Castling(gameState, this, destination, gameState.getPieceOn(m_piecePosition.offsetBy(new int[]{-4, 0})));

            if (pieceOnDestination == null && destination.equals(m_piecePosition.offsetBy(CASTLING_MOVES_OFFSETS[1])))
                return new Castling(gameState, this, destination, gameState.getPieceOn(m_piecePosition.offsetBy(new int[]{3, 0})));
        }

        return null;
    }

    /**
     * Méthode pour savoir de quel type de pièce il s'agit
     * @return le type de pièce
     */
    @Override
    public PieceType getPieceName() {
        return PieceType.KING;
    }


}
