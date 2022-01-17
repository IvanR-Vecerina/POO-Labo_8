package engine.pieces;

import chess.PieceType;
import chess.PlayerColor;
import engine.Board;
import engine.BoardPos2D;
import engine.moves.Attack;
import engine.moves.Move;

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

    public Knight(final BoardPos2D piecePosition, final PlayerColor pieceTeam) {
        super(piecePosition, pieceTeam);
    }

    @Override
    public Move isPieceLegalMove(Board board, BoardPos2D destination) {
        Piece pieceOnDestination = board.getPieceOnPosition(destination);

        for (final int[] currentCandidate : CANDIDATE_MOVES_OFFSETS){
            if (m_piecePosition.offsetBy(currentCandidate).equals(destination))
                if (pieceOnDestination == null) {
                    return new Move(board, this, destination);
                } else if (pieceOnDestination.getColor() != m_pieceColour) {
                    return new Attack(board, this, destination, pieceOnDestination);
                }
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
        return PieceType.KNIGHT;
    }
}
