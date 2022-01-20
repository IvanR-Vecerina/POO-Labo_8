package engine.pieces;

import chess.PieceType;
import chess.PlayerColor;
import engine.Game;
import engine.BoardPos2D;
import engine.moves.Attack;
import engine.moves.Move;

import java.util.List;

/**
 * Classe Knight, qui hérite de la classe Piece. Cette classe permet la représentation d'un cavalier sur le jeu.
 * Elle permet également de tester si le mouvement demandé correspond à un mouvement autorisé par le cavalier.
 *
 * @author Ivan Vecerina
 * @author (Alice Grunder)
 */
public class Knight extends Piece{

    private final static int[][] CANDIDATE_MOVES_OFFSETS = {
            {-2,-1},
            {-2, 1},
            {-1,-2},
            {-1, 2},
            { 1,-2},
            { 1, 2},
            { 2,-1},
            { 2, 1}
    };

    /**
     * Constructeur du cavalier
     * @param piecePosition Position ou le cavalier est placé
     * @param pieceColour Couleur de la pièce
     */
    public Knight(final BoardPos2D piecePosition, final PlayerColor pieceColour) {
        super(piecePosition, pieceColour);
    }

    /**
     * Méthode pour savoir de quel type de pièce il s'agit
     * @return le type de pièce
     */
    @Override
    public PieceType getPieceName() {
        return PieceType.KNIGHT;
    }

    /**
     * Redéfinition de la méthode isPieceLegalMove de Piece. Cette méthode permet de tester si le move tenté est légal
     * pour le cavalier.
     * @param gameState état du reste du jeu
     * @param destination position d'arrivée du déplacement tenté
     * @return null si le mouvement n'est pas légal pour le cavalier,
     *         Attack si le cavalier mange une pièce
     *         Move si le cavalier ne fait que se déplacer
     */
    @Override
    public Move isPieceLegalMove(Game gameState, BoardPos2D destination) {
        Piece pieceOnDestination = gameState.getPieceOn(destination);

        for (final int[] currentCandidate : CANDIDATE_MOVES_OFFSETS){
            if (m_piecePosition.offsetBy(currentCandidate).equals(destination))
                if (pieceOnDestination == null) {
                    return new Move(gameState, this, destination);
                } else if (pieceOnDestination.getColor() != m_pieceColour) {
                    return new Attack(gameState, this, destination, pieceOnDestination);
                }
        }
        return null;
    }

}
