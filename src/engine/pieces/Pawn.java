package engine.pieces;

import chess.PieceType;
import chess.PlayerColor;
import engine.Board;
import engine.BoardPos2D;
import engine.moves.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Pawn extends Piece {
    private final int pawnDirection;

    private final static int[][] CANDIDATE_MOVES_OFFSETS = {
            {-1, 1},
            { 0, 1},
            { 0, 2},
            { 1, 1}
    };

    public Pawn(final BoardPos2D piecePosition, final PlayerColor pieceTeam) {
        super(piecePosition, pieceTeam);
        pawnDirection = pieceTeam == PlayerColor.WHITE ? 1 : -1;
    }

    @Override
    public Move isPieceLegalMove(Board board, BoardPos2D destination) {
        Piece pieceOnDestination = board.getPieceOnPosition(destination);

        if (pieceOnDestination == null && destination.equals(m_piecePosition.offsetBy(CANDIDATE_MOVES_OFFSETS[1])))
            return new Move(board, this, destination);

        if (!this.hasMoved && destination.equals(m_piecePosition.offsetBy(CANDIDATE_MOVES_OFFSETS[2])) && board.getPieceOnPosition(m_piecePosition.offsetBy(CANDIDATE_MOVES_OFFSETS[1])) == null)
            return new PawnJump(board, this, destination, pawnDirection);

        if (destination.equals(m_piecePosition.offsetBy(CANDIDATE_MOVES_OFFSETS[0])) ||
                destination.equals(m_piecePosition.offsetBy(CANDIDATE_MOVES_OFFSETS[3])))
        {
            if (pieceOnDestination != null)
                return new Attack(board, this, destination, pieceOnDestination);

            if (destination == board.getPositionEnPassant())
                return new EnPassant(board, this, destination, board.getPieceOnPosition(destination.getX(), m_piecePosition.getY()));
        }

        return null;
    }


    @Override
    public List<Move> calculateLegalMoves(Board board) {
        BoardPos2D candidateDestPosition;
        final List<Move> legalMoves = new ArrayList<>();

        for (final int[] currentCandidate : CANDIDATE_MOVES_OFFSETS){

            candidateDestPosition = m_piecePosition.offsetBy(currentCandidate);

            if (candidateDestPosition.isValidPos())
            {
                if(board.getPieceOnPosition(candidateDestPosition) == null) {
                    legalMoves.add(new Move(board, this, candidateDestPosition));
                } else {
                    //final Piece =
                }
            }
        }

        return Collections.unmodifiableList(List.copyOf(legalMoves));
    }

    @Override
    public PieceType getPieceName() {
        return PieceType.PAWN;
    }

    public int getPawnDirection() {
        return pawnDirection;
    }
}
