package engine.pieces;

import engine.Board;
import engine.BoardPos2D;

public class PieceUtils {
    public static final int[] DL = {-1, -1};
    public static final int[] L  = {-1,  0};
    public static final int[] UL = {-1,  1};
    public static final int[] D  = { 0, -1};
    public static final int[] U  = { 0,  1};
    public static final int[] DR = { 1, -1};
    public static final int[] R  = { 1,  0};
    public static final int[] UR = { 1,  1};

    public static boolean isPathClear(Board board, BoardPos2D startPosition, BoardPos2D endPosition, int dx, int dy){
        BoardPos2D tmpPosition = startPosition;

        int[] vector = {dx/Math.abs(dx), dy/Math.abs(dy)};

        do {
            tmpPosition = tmpPosition.offsetBy(vector);

            if (board.getPieceOnPosition(tmpPosition) != null) {
                return false;
            }
        } while (!tmpPosition.equals(endPosition));

        return true;
    }
}
