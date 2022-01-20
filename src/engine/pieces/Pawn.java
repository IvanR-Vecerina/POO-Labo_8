package engine.pieces;

import chess.PieceType;
import chess.PlayerColor;
import engine.Game;
import engine.BoardPos2D;
import engine.moves.*;

import java.util.List;

public class Pawn extends MoveTrackedPiece {
    //private final int pawnDirection;

    private final static int[][] CANDIDATE_MOVES_OFFSETS = {
            {-1, 1},
            { 0, 1},
            { 0, 2},
            { 1, 1}
    };

    /**
     * Constructeur du pion
     * @param piecePosition Position ou le pion est placé
     * @param pieceColour Couleur de la pièce
     */
    public Pawn(final BoardPos2D piecePosition, final PlayerColor pieceColour) {
        super(piecePosition, pieceColour);
        //pawnDirection = pieceColour == PlayerColor.WHITE ? 1 : -1;
    }

    /**
     * Redéfinition de la méthode isPieceLegalMove de Piece. Cette méthode permet de tester si le move tenté est légal
     * pour le fou.
     * @param gameState état du reste du jeu
     * @param destination position d'arrivée du déplacement tenté
     * @return null si le mouvement n'est pas légal pour le pion,
     *         Attack si le pion mange une pièce en diagonale de lui
     *         Move si le pion ne fait que se déplacer de 1 en ligne droite
     *         PawnJump si le pion fait un premier déplacement de 2
     *         EnPassant si le pion fait une prise en passant valide
     */
    @Override
    public Move isPieceLegalMove(Game gameState, BoardPos2D destination) {
        int pawnDirection = gameState.getCurrentPayer().getPawnDirection();
        Piece pieceOnDestination = gameState.getPieceOn(destination);

        //Test si c'est un déplacement normal
        if (pieceOnDestination == null &&
            destination.equals(m_piecePosition.offsetBy(CANDIDATE_MOVES_OFFSETS[1], pawnDirection)))
            return new Move(gameState, this, destination);

        //Test si c'est un premier déplacement de 2
        if (!hasMoved &&
            destination.equals(m_piecePosition.offsetBy(CANDIDATE_MOVES_OFFSETS[2], pawnDirection)) &&
            gameState.getPieceOn(m_piecePosition.offsetBy(CANDIDATE_MOVES_OFFSETS[1], pawnDirection)) == null &&
            gameState.getPieceOn(m_piecePosition.offsetBy(CANDIDATE_MOVES_OFFSETS[2], pawnDirection)) == null)
            return new PawnJump(gameState, this, destination, pawnDirection);

        //Test si c'est une attaque, et si oui, s'il s'agit d'une attaque normale ou d'une tentative de prise en passant
        if (destination.equals(m_piecePosition.offsetBy(CANDIDATE_MOVES_OFFSETS[0], pawnDirection)) ||
            destination.equals(m_piecePosition.offsetBy(CANDIDATE_MOVES_OFFSETS[3], pawnDirection)))
        {
            if (pieceOnDestination != null)
                return new Attack(gameState, this, destination, pieceOnDestination);

            if (destination.equals(gameState.getPositionEnPassant()))
                return new EnPassant(gameState, this, destination, gameState.getPieceOn(destination.getX(), m_piecePosition.getY()));
        }

        return null;
    }

    /**
     * Méthode pour savoir de quel type de pièce il s'agit
     * @return le type de pièce
     */
    @Override
    public PieceType getPieceName() {
        return PieceType.PAWN;
    }


}
