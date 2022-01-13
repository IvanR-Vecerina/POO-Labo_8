package engine;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Pawn extends Piece {
    private final static int[][] CANDIDATE_MOVES_OFFSETS = {
            {-1, 1},
            { 0, 1},
            { 0, 2},
            { 1, 1}
    };

    Pawn(final BoardPos2D piecePosition, final TeamColour pieceTeam) {
        super(PieceType.KNIGHT, piecePosition, pieceTeam);
    }

    @Override
    public Move isPieceLegalMove(Board board, BoardPos2D destination) {
        if (!this.hasMoved && destination.equals(m_piecePosition.offsetBy(CANDIDATE_MOVES_OFFSETS[2])))
            return new MoveEnPassant(board, this, destination);

        if (board.getPieceOnPosition(destination) != null && destination.equals(m_piecePosition.offsetBy(CANDIDATE_MOVES_OFFSETS[1])))
            return new MoveEnPassant(board, this, destination);

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
}
