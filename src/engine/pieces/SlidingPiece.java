package engine.pieces;

import chess.PlayerColor;
import engine.BoardPos2D;
import engine.Game;
import engine.moves.Attack;
import engine.moves.Move;

public abstract class SlidingPiece extends Piece{
    SlidingPiece(BoardPos2D piecePosition, PlayerColor pieceColour) {
        super(piecePosition, pieceColour);
    }

    private static boolean checkMovePath(Game gameState, BoardPos2D startPosition, BoardPos2D endPosition, int dx, int dy){
        BoardPos2D tmpPosition = startPosition;

        int[] vector = { dx == 0 ? 0 : dx/Math.abs(dx), dy == 0 ? 0 : dy/Math.abs(dy)};

        tmpPosition = tmpPosition.offsetBy(vector);

        while (!tmpPosition.equals(endPosition)) {

            if (gameState.getPieceOn(tmpPosition) != null) {
                return false;
            }

            tmpPosition = tmpPosition.offsetBy(vector);
        }

        return true;
    }

    protected Move pickMoveType(Game gameState, BoardPos2D destination, int dx, int dy) {
        if (checkMovePath(gameState, m_piecePosition, destination, dx, dy)) {
            Piece tmp = gameState.getPieceOn(destination);

            if (tmp == null) {
                return new Move(gameState, this, destination);
            } else if (tmp.getColor() != m_pieceColour) {
                return new Attack(gameState, this, destination, tmp);
            }
        }
        return null;
    }
}
