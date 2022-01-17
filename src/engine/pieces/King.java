package engine.pieces;

import chess.PieceType;
import chess.PlayerColor;
import engine.Board;
import engine.BoardPos2D;
import engine.moves.Attack;
import engine.moves.Castling;
import engine.moves.Move;

import java.util.List;

import static engine.pieces.PieceUtils.*;
import static engine.pieces.PieceUtils.UL;

public class King extends Piece{

    private final static int[][] CASTLING_MOVES_OFFSETS = {{-2, 0}, {2, 0}};

    public King(BoardPos2D piecePosition, PlayerColor pieceColour) {
        super(piecePosition, pieceColour);
    }

    @Override
    public Move isPieceLegalMove(Board board, BoardPos2D destination) {
        Piece pieceOnDestination = board.getPieceOnPosition(destination);
        int deltaX = destination.getX() - m_piecePosition.getX();
        int deltaY = destination.getY() - m_piecePosition.getY();

        if (Math.abs(deltaX + deltaY) == 1 || Math.abs(deltaX * deltaY) == 1){

            if (pieceOnDestination == null) {
                return new Move(board, this, destination);
            } else if (pieceOnDestination.getColor() != m_pieceColour) {
                return new Attack(board, this, destination, pieceOnDestination);
            }
        }


        if (pieceOnDestination == null && destination.equals(m_piecePosition.offsetBy(CASTLING_MOVES_OFFSETS[0])))
            return new Castling(board, this, destination, board.getPieceOnPosition(m_piecePosition.offsetBy(new int[]{-4, 0})));

        if (pieceOnDestination == null && destination.equals(m_piecePosition.offsetBy(CASTLING_MOVES_OFFSETS[1])))
            return new Castling(board, this, destination, board.getPieceOnPosition(m_piecePosition.offsetBy(new int[]{3, 0})));

        return null;
    }

    @Override
    public List<Move> calculateLegalMoves(Board board) {
        return null;
    }

    @Override
    public PieceType getPieceName() {
        return PieceType.KING;
    }
}
