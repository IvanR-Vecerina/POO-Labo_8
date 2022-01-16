package engine;

import chess.ChessView;
import chess.PlayerColor;
import engine.pieces.*;

import java.util.LinkedList;

public class Board {
    private final Piece[][] board;
    private final ChessView view;

    private LinkedList<Piece> whitePieces;
    private LinkedList<Piece> blackPieces;
    private PlayerColor playerTurn;

    private BoardPos2D positionEnPassant;

    public Board(ChessView view){

        board = new Piece[8][8];
        whitePieces = new LinkedList<>();
        blackPieces = new LinkedList<>();


        this.view = view;

        playerTurn = PlayerColor.WHITE;

        initOneColorPieces(whitePieces, PlayerColor.BLACK);
        initOneColorPieces(blackPieces, PlayerColor.WHITE);

        this.reset();
    }

    public boolean tryMove(BoardPos2D from, BoardPos2D to){

//        if(board[from.getX()][from.getY()] != null && board[from.getX()][from.getY()].getColor() == playerTurn){
//            if(board[to.getX()][to.getY()] == null ||
//               board[to.getX()][to.getY()].getColor() != board[to.getX()][to.getY()].getColor()){
//
//            }
//        }
//        return false;
        getPieceOnPosition(from).isPieceLegalMove(this, to).execute();

        return false;
    }

    private void reset(){

    }

    public Piece getPieceOnPosition(BoardPos2D boardPos2D){
        return board[boardPos2D.getX()][boardPos2D.getY()];
    }

    public Piece getPieceOnPosition(int x, int y){
        return board[x][y];
    }

    private void initOneColorPieces(LinkedList<Piece> list, PlayerColor color){
        int backLine, fronLine;
        if(color == PlayerColor.WHITE){
            backLine = 0;
            fronLine = 1;
        }else{
            backLine = 7;
            fronLine = 6;
        }

        list.add(new King(new BoardPos2D(4, backLine), color));
        list.add(new Queen(new BoardPos2D(3, backLine), color));
        list.add(new Bishop(new BoardPos2D(2, backLine), color));
        list.add(new Bishop(new BoardPos2D(5, backLine), color));
        list.add(new Knight(new BoardPos2D(1, backLine), color));
        list.add(new Knight(new BoardPos2D(6, backLine), color));
        list.add(new Rook(new BoardPos2D(7, backLine), color));
        list.add(new Rook(new BoardPos2D(0, backLine), color));

        for(int i = 0; i < 8; i++){
            list.add(new Pawn(new BoardPos2D(i, fronLine), color));
        }

        for (Piece piece : list) {
            board[piece.getX()][piece.getY()] = piece;
            view.putPiece(board[piece.getX()][piece.getY()].getPieceName(),
                          board[piece.getX()][piece.getY()].getColor(),
                          piece.getX(), piece.getY());
        }
    }

    public void movePiece(BoardPos2D from, BoardPos2D to) {
        view.putPiece(getPieceOnPosition(from).getPieceName(), playerTurn, to.getX(), to.getY());
        view.removePiece(from.getX(), from.getY());

        board[to.getX()][to.getY()] = board[from.getX()][from.getY()];
        board[from.getX()][from.getY()] = null;
    }

    public void removeEnPassantPawn() {
        if (positionEnPassant.getY() == 2)
            view.removePiece(positionEnPassant.getX(), 3);

        if (positionEnPassant.getY() == 5)
            view.removePiece(positionEnPassant.getX(), 4);
    }

    public void killPiece(Piece piece) {
        if (piece.getColor() == PlayerColor.WHITE) {
            whitePieces.remove(piece);
        }
        if (piece.getColor() == PlayerColor.BLACK) {
            blackPieces.remove(piece);
        }
    }

    public PlayerColor getPlayerTurn() {
        return playerTurn;
    }

    public void setPositionEnPassant(BoardPos2D positionEnPassant) {
        this.positionEnPassant = positionEnPassant;
    }

    public BoardPos2D getPositionEnPassant() {
        return positionEnPassant;
    }

    public void removeEnPassant() {
        setPositionEnPassant(null);
    }
}
