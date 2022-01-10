package engine;

public class Board {
    static Piece[][] board;

    public Board(){
        this.reset();
    }

    private void reset(){

    }

    public Piece getPieceOnPosition(BoardPos2D boardPos2D){
        return board[boardPos2D.getX()][boardPos2D.getY()];
    }

}
