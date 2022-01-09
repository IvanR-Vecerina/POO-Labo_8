package engine;

import java.util.ArrayList;
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

    Knight(final int[] piecePosition, final Alliance pieceTeam) {
        super(piecePosition, pieceTeam);
    }


    @Override
    public List<int[]> calculateLegalMoves(Board board) {
        int[] candidateDestPosition = new int[2];
        final List<int[]> legalMoves = new ArrayList<>();

        for (final int[] currentCandidate : CANDIDATE_MOVES_OFFSETS){

            candidateDestPosition[0] = m_piecePosition[0] + currentCandidate[0];
            candidateDestPosition[1] = m_piecePosition[1] + currentCandidate[1];

            if (((candidateDestPosition[0] >= 0) && (candidateDestPosition[0] < 8)) &&
                    ((candidateDestPosition[1] >= 0) && (candidateDestPosition[1] < 8)))
            {
                if(board.getPieceOnPosition() == null) {
                    legalMoves.add(new int[2]);
                } else {
                    //final Piece =
                }
            }
        }

        return null;
    }
}
