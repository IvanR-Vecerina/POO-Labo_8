package engine.pieces;

import chess.PieceType;
import chess.PlayerColor;
import engine.Game;
import engine.BoardPos2D;
import engine.moves.Move;

import java.util.List;

import static engine.pieces.PieceUtils.*;

public class Queen extends SlidingPiece{

    private final static int[][] CANDIDATE_MOVE_VECTORS_OFFSETS = {U, D, L, R, DR, DL, UR, UL};

    public Queen(BoardPos2D piecePosition, PlayerColor pieceColour) {
        super(piecePosition, pieceColour);
    }

    @Override
    public Move isPieceLegalMove(Game gameState, BoardPos2D destination) {
        int deltaX = destination.getX() - m_piecePosition.getX();
        int deltaY = destination.getY() - m_piecePosition.getY();

        if (Math.abs(deltaX) == Math.abs(deltaY) || deltaX == 0 || deltaY == 0){
            return pickMoveType(gameState, destination, deltaX, deltaY);
        }
        return null;
    }

    @Override
    public List<Move> calculateLegalMoves(Game gameState) {
//        BoardPos2D candidateDestPosition;
//        final List<Move> legalMoves = new ArrayList<>();
//
//        for (final int[] currentCandidate : CANDIDATE_MOVE_VECTORS_OFFSETS){
//            candidateDestPosition = m_piecePosition.offsetBy(currentCandidate);
//
//            while (candidateDestPosition.isValidPos()){
//                if (board.getPieceOnPosition(candidateDestPosition) == null){
//                    legalMoves.add(new Move(board, this, candidateDestPosition));
//                }else{
//                    if (board.getPieceOnPosition(candidateDestPosition).m_pieceColour != m_pieceColour){
//                        legalMoves.add(new Move(board, this, candidateDestPosition));
//                    }
//                    break;
//                }
//
//                candidateDestPosition = candidateDestPosition.offsetBy(currentCandidate);
//            }
//        }
//
//        return Collections.unmodifiableList(List.copyOf(legalMoves));
        return null;
    }

    @Override
    public PieceType getPieceName() {
        return PieceType.QUEEN;
    }


}
