package engine;

import chess.ChessController;
import chess.ChessView;
import chess.PieceType;
import chess.PlayerColor;

public class ChessGame implements ChessController {

    Board board;

    @Override
    public void start(ChessView view) {
        view.startView();
        //view.putPiece(PieceType.KING, PlayerColor.BLACK, 1, 2);
        board = new Board(view);
    }

    @Override
    public boolean move(int fromX, int fromY, int toX, int toY) {
        //System.out.println(String.format("%d, %d to %d, %d", fromX, fromY, toX, toY));

        //board.tryMove(fromX, fromY, toX, toY);

        return false;
    }

    @Override
    public void newGame() {

    }
}
