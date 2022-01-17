package engine;

import chess.PlayerColor;
import engine.pieces.Piece;

import java.util.LinkedList;

public class AltChessPlayer {

    private final PlayerColor color;
    private final int pawnDirection;

    private LinkedList<Piece> pieces;

    private boolean playerLCastlable;
    private boolean playerRCastlable;

    AltChessPlayer(PlayerColor color, int pawnDirection) {
        this.color = color;
        this.pawnDirection = pawnDirection;
    }

    void initPlayer() {
        this.playerLCastlable = true;
        this.playerRCastlable = true;
        this.pieces.clear();
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
}
