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

    /**
     * Constructeur d'un player.
     * @param color couleur d'un joueur (noir ou blanc)
     * @param pawnDirection sens dans lequel vont se déplacer les pions.
     */
    Player(PlayerColor color, int pawnDirection) {
        this.color = color;
        this.pawnDirection = pawnDirection;
    }

    /**
     * Réinitialise le joueur et ses pièces pour un début de partie
     */
    void resetPlayer() {
        playerLCastlable = true;
        playerRCastlable = true;
        initPieceSet();
    }

    /**
     * Initialisation de toutes les pièces d'une couleur
     */
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

    /**
     * Supprime une pièce de la liste de pièces possédées par un joueur
     * @param piece pièce à supprimer
     */
    void removePiece(Piece piece) {
        this.pieces.remove(piece);
    }

    /**
     * Ajoute une pièce à la liste de pièce du joueur
     * @param piece pièce à ajouter
     */
    void addPiece(Piece piece) {
        this.pieces.add(piece);
    }

    /**
     * Méthode utilisée pour savoir si le joueur peut faire un grand rock
     * @return true si le joueur peut faire un grand rock, false sinon
     */
    public boolean isPlayerLCastlable() {
        return playerLCastlable;
    }

    /**
     * Méthode utilisée pour savoir si le joueur peut faire un petit rock
     * @return true si le joueur peut faire un petit rock, false sinon
     */
    public boolean isPlayerRCastlable() {
        return playerRCastlable;
    }

    /**
     * Méthode pour connaître la couleur du joueur
     * @return la couleur du joueur
     */
    public PlayerColor getColor() {
        return color;
    }

    /**
     * Méthode permettant de connaître le sens des pions
     * @return un entier signé indiquand le sens des pion (normalement 1 ou -1)
     */
    public int getPawnDirection() {
        return pawnDirection;
    }

    /**
     * Méthode utilisée pour accéder directement au roi du joueur
     * @return une référence sur le roi de ce joueur
     */
    public Piece getKing() {
        return king;
    }

    /**
     * Méthode utilisée pour récupérer la liste des pièces de ce joueur
     * @return liste contenant les pièces encore vivantes du joueur.
     */
    public ArrayList<Piece> getPieces() {
        return pieces;
    }

    /**
     * Enlève la possibilité de faire un roque à droite
     */
    public void removeCastlelingL(){
        playerLCastlable = false;
    }

    /**
     * Enlève la possibilité de faire un roque à gauche
     */
    public void removeCastlelingR(){
        playerRCastlable = false;
    }

    /**
     * Méthode permettant de savoir si une pièce du joueur peut attaquer une case particulière
     * @param gameState etat actuel du jeu
     * @param posAttacked position dont on veut test l'ataquabilité
     * @return retourne true si la case est attaquable,
     *         retourne false sinon
     */
    public boolean canAttack(Game gameState, BoardPos2D posAttacked){
        for(Piece piece : pieces){
            if(piece.isPieceLegalMove(gameState, posAttacked) != null){
                return true;
            }
        }
        return false;
    }
}
