package engine.pieces;

import chess.PieceType;
import chess.PlayerColor;
import engine.Board;
import engine.BoardPos2D;
import engine.moves.Move;

import java.util.List;

import static engine.pieces.PieceUtils.*;
import static engine.pieces.PieceUtils.UL;

public class King extends Piece{

    private final static int[][] CANDIDATE_MOVES_OFFSETS = {U, D, L, R, DR, DL, UR, UL};
    private final static int[][] CASTLING_MOVES_OFFSETS = {{-2, 0}, {2, 0}};

    public King(BoardPos2D piecePosition, PlayerColor pieceColour) {
        super(piecePosition, pieceColour);
    }

    @Override
    public Move isPieceLegalMove(Board board, BoardPos2D destination) {
        for (final int[] currentCandidate : CANDIDATE_MOVES_OFFSETS){
            if (m_piecePosition.offsetBy(currentCandidate).equals(destination))
                return null;
        }
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
