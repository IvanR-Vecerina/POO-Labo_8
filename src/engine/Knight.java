package engine;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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

    Knight(final BoardPos2D piecePosition, final TeamColour pieceTeam) {
        super(PieceType.KNIGHT, piecePosition, pieceTeam);
    }

    @Override
    public boolean isPieceLegalMove(Board board, BoardPos2D destination) {
        for (final int[] currentCandidate : CANDIDATE_MOVES_OFFSETS){
            if (m_piecePosition.offsetBy(currentCandidate).equals(destination))
                return true;
        }
        return false;
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
