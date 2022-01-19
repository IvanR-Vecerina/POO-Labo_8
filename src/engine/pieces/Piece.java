package engine.pieces;

import chess.PieceType;
import chess.PlayerColor;
import engine.Game;
import engine.BoardPos2D;
import engine.moves.Move;

import java.util.List;
import java.util.Objects;

public abstract class Piece {
    protected BoardPos2D m_piecePosition;
    protected final PlayerColor m_pieceColour;

    Piece(final BoardPos2D piecePosition, final PlayerColor pieceColour) {
        m_piecePosition = piecePosition;
        m_pieceColour   = pieceColour;
    }

    public abstract Move isPieceLegalMove(Game gameState, BoardPos2D destination);

    public abstract List<Move> calculateLegalMoves(final Game gameState);

    public abstract PieceType getPieceName();

    public void setPiecePosition(BoardPos2D m_piecePosition) {
        this.m_piecePosition = m_piecePosition;
    }

    public PlayerColor getColor(){
        return m_pieceColour;
    }

    public BoardPos2D getPosition() {
        return m_piecePosition;
    }

    public int getX(){
        return m_piecePosition.getX();
    }

    public int getY(){
        return m_piecePosition.getY();
    }

    // Getters
    // Get next turn magic moves
    // Get next turn legal moves


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Piece piece = (Piece) o;
        return Objects.equals(m_piecePosition, piece.m_piecePosition) &&
                m_pieceColour == piece.m_pieceColour &&
                this.getPieceName() == piece.getPieceName();
    }

    @Override
    public int hashCode() {
        return Objects.hash(m_piecePosition, m_pieceColour);
    }
}
