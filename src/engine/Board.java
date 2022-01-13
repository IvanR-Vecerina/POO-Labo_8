package engine;

import chess.ChessView;
import chess.PieceType;
import chess.PlayerColor;

import java.util.LinkedList;
import java.util.List;

public class Board {
    Piece[][] board = new Piece[8][8];
    LinkedList<Piece> whitePieces = new LinkedList<>();
    LinkedList<Piece> blackPieces = new LinkedList<>();
    ChessView view;

    public Board(ChessView view){

        this.view = view;

        initOneColorPieces(whitePieces, PlayerColor.BLACK);
        initOneColorPieces(blackPieces, PlayerColor.WHITE);

        /*
        int tmp1, tmp2;
        for(int i = 0; i < 2; ++i){
            for(int j = 0; j < 16; ++j){
                tmp1 = j % 8;
                tmp2 = j / 8 + i * 6;
                view.putPiece(board[tmp1][tmp2].getPieceName(), board[tmp1][tmp2].getColor(), tmp1, tmp2);
            }
        }*/

        this.reset();
    }

    private void reset(){

    }

    public Piece getPieceOnPosition(BoardPos2D boardPos2D){
        return board[boardPos2D.getX()][boardPos2D.getY()];
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

}
