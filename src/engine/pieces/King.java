package engine.pieces;

import chess.PieceType;
import chess.PlayerColor;
import engine.AltChessGame;
import engine.BoardPos2D;
import engine.moves.Attack;
import engine.moves.Castling;
import engine.moves.Move;

import java.util.List;

public class King extends MoveTrackedPiece {

    private final static int[][] CASTLING_MOVES_OFFSETS = {{-2, 0}, {2, 0}};

    public King(BoardPos2D piecePosition, PlayerColor pieceColour) {
        super(piecePosition, pieceColour);
    }

    @Override
    public Move isPieceLegalMove(AltChessGame gameState, BoardPos2D destination) {
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

    @Override
    public List<Move> calculateLegalMoves(AltChessGame gameState) {
        return null;
    }

    @Override
    public PieceType getPieceName() {
        return PieceType.KING;
    }
}
