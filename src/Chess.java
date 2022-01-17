import chess.ChessController;
import chess.ChessView;
import chess.views.gui.GUIView;
import engine.AltChessGame;

public class Chess {
    public static void main(String[] args) {
        ChessController chessController = new AltChessGame();

        ChessView view = new GUIView(chessController);

        chessController.start(view);
    }

}
