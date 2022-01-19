package engine;

import engine.pieces.Piece;

public class Board {
    private final Piece[][] board;

    public Board() {
        this.board = new Piece[8][8];
    }

    public Piece getPieceOn(BoardPos2D boardPos2D){
        return board[boardPos2D.getX()][boardPos2D.getY()];
    }

    public Piece getPieceOn(int x, int y){
        return board[x][y];
    }

    public Piece setPieceOn(Piece piece, BoardPos2D boardPos2D){
        return board[boardPos2D.getX()][boardPos2D.getY()] = piece;
    }

    public Piece setPieceOn(Piece piece, int x, int y){
        return board[x][y] = piece;
    }
}
