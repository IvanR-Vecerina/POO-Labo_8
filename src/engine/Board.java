package engine;

public class Board {
    static Piece[][] board;

    public Board(){
        this.reset();
    }

    private void reset(){

    }

    public Piece getPieceOnPosition(int x, int y){
        return board[x][y];
    }

    public Object getPieceOnPosition() {
        return null;
    }
}
