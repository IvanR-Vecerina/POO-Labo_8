package engine.pieces;

import chess.PieceType;
import chess.PlayerColor;
import engine.Game;
import engine.BoardPos2D;
import engine.moves.*;

import java.util.List;

public class Pawn extends MoveTrackedPiece {
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
    public Move isPieceLegalMove(Game gameState, BoardPos2D destination) {
        Piece pieceOnDestination = gameState.getPieceOn(destination);

        if (pieceOnDestination == null && destination.equals(m_piecePosition.offsetBy(CANDIDATE_MOVES_OFFSETS[1], pawnDirection)))
            return new Move(gameState, this, destination);

        if (!hasMoved &&
                destination.equals(m_piecePosition.offsetBy(CANDIDATE_MOVES_OFFSETS[2], pawnDirection)) &&
                gameState.getPieceOn(m_piecePosition.offsetBy(CANDIDATE_MOVES_OFFSETS[1], pawnDirection)) == null)
            return new PawnJump(gameState, this, destination, pawnDirection);

        if (destination.equals(m_piecePosition.offsetBy(CANDIDATE_MOVES_OFFSETS[0], pawnDirection)) ||
                destination.equals(m_piecePosition.offsetBy(CANDIDATE_MOVES_OFFSETS[3], pawnDirection)))
        {
            if (pieceOnDestination != null)
                return new Attack(gameState, this, destination, pieceOnDestination);

            if (destination.equals(gameState.getPositionEnPassant()))
                return new EnPassant(gameState, this, destination, gameState.getPieceOn(destination.getX(), m_piecePosition.getY()));
        }

        return null;
    }


    @Override
    public List<Move> calculateLegalMoves(Game gameState) {
//        BoardPos2D candidateDestPosition;
//        final List<Move> legalMoves = new ArrayList<>();
//
//        for (final int[] currentCandidate : CANDIDATE_MOVES_OFFSETS){
//
//            candidateDestPosition = m_piecePosition.offsetBy(currentCandidate);
//
//            if (candidateDestPosition.isValidPos())
//            {
//                if(board.getPieceOnPosition(candidateDestPosition) == null) {
//                    legalMoves.add(new Move(board, this, candidateDestPosition));
//                } else {
//                    //final Piece =
//                }
//            }
//        }
//
//        return Collections.unmodifiableList(List.copyOf(legalMoves));
        return null;
    }

    @Override
    public PieceType getPieceName() {
        return PieceType.PAWN;
    }

    public int getPawnDirection() {
        return pawnDirection;
    }

}
