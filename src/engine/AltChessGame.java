package engine;

import chess.ChessView;
import chess.PlayerColor;
import engine.pieces.Piece;

import java.util.LinkedList;

public class AltChessGame implements chess.ChessController
{
    // board
    private AltChessBoard board;

    // view
    private ChessView view;

    // turn
    private PlayerColor playerTurn;

    // players
    private AltChessPlayer[] players =
            {
                    new AltChessPlayer(PlayerColor.WHITE,  1),
                    new AltChessPlayer(PlayerColor.BLACK, -1)
            };


    /**
     * Démarre la logique (contrôleur) du programme.
     * Appelé une fois (voir Chess.main())
     *
     * @param view la vue à utiliser
     */
    @Override
    public void start(ChessView view) {
        view.startView();
    }

    /**
     * Appelé lorsque l'utilisateur a demandé un déplacement de la position X à la position Y.
     * La position 0, 0 est en bas à gauche de l'échiquier.
     *
     * @param fromX
     * @param fromY
     * @param toX
     * @param toY
     * @return true si le mouvement a pu avoir lieu, false dans le cas contraire.
     */
    @Override
    public boolean move(int fromX, int fromY, int toX, int toY) {
        System.out.println("(" + fromX + ", " + fromY + ") ---> (" + toX + ", " + toY + ")");

        // Checks préalables


        // Piece.CheckMove -> Move

        // Move.execute


        return false;
    }

    /**
     * Démarre une nouvelle partie. L'échiquier doit être remis dans sa position initiale.
     */
    @Override
    public void newGame() {
        this.playerTurn = PlayerColor.WHITE;

        PlayerColor tmp_color;

        // init bottom player
        tmp_color = players[0].getColor();


    }

    public void addPiece(Piece piece){
        this.board.setPieceOn(piece, piece.getPosition());
        this.view.putPiece(piece.getPieceName(), piece.getColor(), piece.getX(), piece.getY());
    }

    public AltChessPlayer getCurrentPayer() {
        return playerTurn == players[0].getColor() ? players[0] : players[1];
    }

    public AltChessPlayer getOtherPlayer() {
        return playerTurn == players[0].getColor() ? players[1] : players[0];
    }

    public void nextTurn(){
        this.playerTurn = PlayerColor.WHITE == this.playerTurn ? PlayerColor.BLACK : PlayerColor.WHITE;
    }
}
