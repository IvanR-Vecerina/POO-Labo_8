package engine.moves;

import engine.Game;
import engine.BoardPos2D;
import engine.pieces.Piece;

public class Move {
    protected final Game gameState;
    protected final Piece pieceToMove;
    protected final BoardPos2D destination;
    protected BoardPos2D oldPos;

    public Move(Game gameState, Piece pieceToMove, BoardPos2D destination) {
        this.gameState = gameState;
        this.pieceToMove = pieceToMove;
        this.destination = destination;
    }

    public boolean doMove(){
        tryMove();
        if(checkMove()){
            execute();
            return true;
        }else{
            rollbackMove();
            return false;
        }
    }

    // Apply move to engine (board + pieceData update)
    protected void tryMove(){
        oldPos = pieceToMove.getPosition();

        gameState.movePieceBoard(pieceToMove, destination);
        gameState.movePieceBoard(null, pieceToMove.getPosition());
        pieceToMove.setPiecePosition(destination);
    }

    protected boolean checkMove(){
        if(gameState.isChecking(gameState)){
            return false;
        }else{
            return true;
        }
    }

    // Rollback changes on engine
    protected void rollbackMove() {
        gameState.movePieceBoard(pieceToMove, oldPos);
        gameState.movePieceBoard(null, pieceToMove.getPosition());
        pieceToMove.setPiecePosition(oldPos);
    }

    protected void execute() {
        gameState.movePiece(pieceToMove, oldPos);
    }

    public BoardPos2D getDestination() {
        return destination;
    }
}
