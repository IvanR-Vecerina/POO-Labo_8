package engine;

import chess.ChessView;
import chess.PieceType;
import chess.PlayerColor;

import java.util.LinkedList;
import java.util.List;

public class Board {
    static Piece[][] board;
    LinkedList<Piece> whitePieces = new LinkedList<>();
    LinkedList<Piece> blackPieces = new LinkedList<>();

    public Board(ChessView view){
        whitePieces.add(new King(new BoardPos2D(4, 0), PlayerColor.WHITE));
        whitePieces.add(new Queen(new BoardPos2D(4, 0), PlayerColor.WHITE));
        whitePieces.add(new King(new BoardPos2D(4, 0), PlayerColor.WHITE));
        whitePieces.add(new King(new BoardPos2D(4, 0), PlayerColor.WHITE));
        whitePieces.add(new King(new BoardPos2D(4, 0), PlayerColor.WHITE));
        whitePieces.add(new King(new BoardPos2D(4, 0), PlayerColor.WHITE));
        whitePieces.add(new King(new BoardPos2D(4, 0), PlayerColor.WHITE));
        whitePieces.add(new King(new BoardPos2D(4, 0), PlayerColor.WHITE));

        for(int i = 0; i < 8; i++){
            whitePieces.add(new King(new BoardPos2D(i, 1), PlayerColor.WHITE));
        }

        this.reset();
    }

    private void reset(){

    }

    public Piece getPieceOnPosition(BoardPos2D boardPos2D){
        return board[boardPos2D.getX()][boardPos2D.getY()];
    }

}
