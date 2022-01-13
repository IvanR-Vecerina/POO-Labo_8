package engine;

import chess.PieceType;
import chess.PlayerColor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Rook extends Piece{

    private final static int[][] CANDIDATE_MOVE_VECTOR_OFFSETS = {
            {-1, 0},
            { 0,-1},
            { 1, 0},
            { 0, 1}
    };

    Rook(BoardPos2D piecePosition, PlayerColor pieceColour) {
        super(piecePosition, pieceColour);
    }

    @Override
    public Move isPieceLegalMove(Board board, BoardPos2D destination) {
        int deltaX = destination.getX() - m_piecePosition.getX();
        int deltaY = destination.getY() - m_piecePosition.getY();

        if (deltaX == 0 || deltaY == 0){
            int[] vector = {deltaX/Math.abs(deltaX), deltaY/Math.abs(deltaY)};

            BoardPos2D posToTest = m_piecePosition.offsetBy(vector);

            while (posToTest.isValidPos()){
                if (posToTest.equals(destination)){
                    return new Move(board, this, destination);
                }
                if (board.getPieceOnPosition(posToTest) != null){
                    break;
                }

                posToTest = posToTest.offsetBy(vector);
            }
        }
        return null;
    }

    @Override
    public List<Move> calculateLegalMoves(Board board) {
        BoardPos2D candidateDestPosition;
        final List<Move> legalMoves = new ArrayList<>();

        for (final int[] currentCandidate : CANDIDATE_MOVE_VECTOR_OFFSETS){
            candidateDestPosition = m_piecePosition.offsetBy(currentCandidate);

            while (candidateDestPosition.isValidPos()){
                if (board.getPieceOnPosition(candidateDestPosition) == null){
                    legalMoves.add(new Move(board, this, candidateDestPosition));
                }else{
                    if (board.getPieceOnPosition(candidateDestPosition).m_pieceColour != m_pieceColour){
                        legalMoves.add(new Move(board, this, candidateDestPosition));
                    }
                    break;
                }

                candidateDestPosition = candidateDestPosition.offsetBy(currentCandidate);
            }
        }

        return Collections.unmodifiableList(List.copyOf(legalMoves));
    }

    @Override
    public PieceType getPieceName() {
        return PieceType.ROOK;
    }
}
