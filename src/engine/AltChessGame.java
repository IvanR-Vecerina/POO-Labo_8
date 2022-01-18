package engine;

import chess.ChessView;
import chess.PlayerColor;
import engine.moves.Move;
import engine.pieces.*;

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

    private BoardPos2D enPassant;


    /**
     * Démarre la logique (contrôleur) du programme.
     * Appelé une fois (voir Chess.main())
     *
     * @param view la vue à utiliser
     */
    @Override
    public void start(ChessView view) {
        this.view = view;
        this.view.startView();
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
        Piece pieceToMove = board.getPieceOn(fromX, fromY);

        // Checks préalables
        if (pieceToMove == null) {
            view.displayMessage("ERROR: Nopiiece moved!");
            return false;
        }

        if (pieceToMove.getColor() != playerTurn) {
            view.displayMessage("ERROR: Not this color's turn!");
            return false;
        }

        if (board.getPieceOn(toX, toY) != null && pieceToMove.getColor() == board.getPieceOn(toX, toY).getColor()) {
            view.displayMessage("ERROR: Destination already occupied by same color piece!");
            return false;
        }

        Move move = pieceToMove.isPieceLegalMove(this, new BoardPos2D(new int[]{toX, toY}));

        if (move == null) {
            view.displayMessage("Error: Piece illegal move");
            return false;
        }

        move.execute();

        nextTurn();

        return true;
    }

    /**
     * Démarre une nouvelle partie. L'échiquier doit être remis dans sa position initiale.
     */
    @Override
    public void newGame() {
        board = new AltChessBoard();
        playerTurn = PlayerColor.WHITE;

        for (AltChessPlayer player : players) {
            player.resetPlayer();

            for (Piece piece : player.getPieces())
                placePiece(piece);
        }
    }

    public void nextTurn(){
        this.playerTurn = PlayerColor.WHITE == this.playerTurn ? PlayerColor.BLACK : PlayerColor.WHITE;
    }

    public AltChessPlayer getCurrentPayer() {
        return playerTurn == players[0].getColor() ? players[0] : players[1];
    }

    public AltChessPlayer getOtherPlayer() {
        return playerTurn == players[0].getColor() ? players[1] : players[0];
    }

    public void placePiece(Piece piece){
        this.board.setPieceOn(piece, piece.getPosition());
        this.view.putPiece(piece.getPieceName(), piece.getColor(), piece.getX(), piece.getY());
    }

    public void killPiece(Piece piece){
        getOtherPlayer().removePiece(piece);
    }

    public void movePiece(BoardPos2D from, BoardPos2D to) {
        Piece pieceToMove = getPieceOn(from);

        view.putPiece(pieceToMove.getPieceName(), pieceToMove.getColor(), to.getX(), to.getY());
        view.removePiece(from.getX(), from.getY());

        board.setPieceOn(pieceToMove, to);
        board.setPieceOn(null, from);
    }

    public Piece getPieceOn(BoardPos2D pos2D){
        return board.getPieceOn(pos2D);
    }

    public Piece getPieceOn(int x, int y){
        return board.getPieceOn(x, y);
    }

    public void setPositionEnPassant(BoardPos2D positionEnPassant) {
        this.enPassant  = positionEnPassant;
    }

    public BoardPos2D getPositionEnPassant() {
        return enPassant;
    }

    public void removeEnPassantPawn() {
        if (enPassant.getY() == 2) {
            view.removePiece(enPassant.getX(), 3);
            board.setPieceOn(null, enPassant.getX(), 3);
        }

        if (enPassant.getY() == 5) {
            view.removePiece(enPassant.getX(), 4);
            board.setPieceOn(null, enPassant.getX(), 4);
        }
    }
}
