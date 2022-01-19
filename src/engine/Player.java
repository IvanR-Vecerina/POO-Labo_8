package engine;

import chess.PlayerColor;
import engine.moves.Move;
import engine.pieces.*;

import java.util.ArrayList;

public class Player {

    private final PlayerColor color;
    private final int pawnDirection;

    private ArrayList<Piece> pieces;
    private King king;

    private boolean playerLCastlable;
    private boolean playerRCastlable;

    Player(PlayerColor color, int pawnDirection) {
        this.color = color;
        this.pawnDirection = pawnDirection;
    }

    void resetPlayer() {
        playerLCastlable = true;
        playerRCastlable = true;
        initPieceSet();
    }

    private void initPieceSet() {
        pieces = new ArrayList<>();

        int kingRowIndex = pawnDirection == 1 ? 0 : 7;
        int pawnRowIndex = kingRowIndex + pawnDirection;

        king = new King(new BoardPos2D(4, kingRowIndex), color);

        pieces.add(new   Rook(new BoardPos2D(0, kingRowIndex), color));
        pieces.add(new   Rook(new BoardPos2D(7, kingRowIndex), color));
        pieces.add(new Knight(new BoardPos2D(1, kingRowIndex), color));
        pieces.add(new Knight(new BoardPos2D(6, kingRowIndex), color));
        pieces.add(new Bishop(new BoardPos2D(2, kingRowIndex), color));
        pieces.add(new Bishop(new BoardPos2D(5, kingRowIndex), color));
        pieces.add(new  Queen(new BoardPos2D(3, kingRowIndex), color));
        pieces.add(king);

        for (int i = 0; i < 8; i++)
            pieces.add(new Pawn(new BoardPos2D(i, pawnRowIndex), color));
    }

    void removePiece(Piece piece) {
        this.pieces.remove(piece);
    }

    void addPiece(Piece piece) {
        this.pieces.add(piece);
    }

    boolean isPlayerLCastlable() {
        return playerLCastlable;
    }

    boolean isPlayerRCastlable() {
        return playerRCastlable;
    }

    public PlayerColor getColor() {
        return color;
    }

    public int getPawnDirection() {
        return pawnDirection;
    }

    public Piece getKing() {
        return king;
    }

    public boolean isChecking(Game board, Piece kingEnemy){
        Move move;
        for(Piece piece : pieces){
            if(piece.isPieceLegalMove(board, kingEnemy.getPosition()) != null){
                return true;
            }
        }
        return false;
    }

    public ArrayList<Piece> getPieces() {
        return pieces;
    }
}
