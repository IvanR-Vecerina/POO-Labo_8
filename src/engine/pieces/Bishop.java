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

import static engine.pieces.PieceUtils.*;

public class Bishop extends Piece{

    private final static int[][] CANDIDATE_MOVE_VECTORS_OFFSETS = {DR, DL, UR, UL};

    public Bishop(BoardPos2D piecePosition, PlayerColor pieceColour) {
        super(piecePosition, pieceColour);
    }

    @Override
    public Move isPieceLegalMove(Board board, BoardPos2D destination) {
        int deltaX = destination.getX() - m_piecePosition.getX();
        int deltaY = destination.getY() - m_piecePosition.getY();

        if (Math.abs(deltaX) == Math.abs(deltaY)){
            if (isPathClear(board, m_piecePosition, destination, deltaX, deltaY)){
                Piece tmp = board.getPieceOnPosition(destination);

                if (tmp == null) {
                    return new Move(board, this, destination);
                } else if (tmp.getColor() != m_pieceColour) {
                    return new Attack(board, this, destination, tmp);
                }
            }
        }
        return null;
    }

    @Override
    public List<Move> calculateLegalMoves(Board board) {
        BoardPos2D candidateDestPosition;
        final List<Move> legalMoves = new ArrayList<>();

        for (final int[] currentCandidate : CANDIDATE_MOVE_VECTORS_OFFSETS){
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
        return PieceType.BISHOP;
    }
}
