package engine;

import engine.pieces.Piece;

/**
 * Classe Board, cette classe stocke un tableau de 8x8 représentant un plateau d'échecs. cela permet de vérifier si une
 * case est occupée ou non.
 *
 * @author Ivan Vecerina
 * @author (Thibault Seem)
 */
public class Board {
    private final Piece[][] board;

    /**
     * Constructeur d'un board
     */
    public Board() {
        this.board = new Piece[8][8];
    }

    /**
     * Méthode permettant d'obtenir une référence sur une pièce à une position BoardPos2D
     * @param boardPos2D position BoardPos2D dont on souhaite récupérer la pièce
     * @return null s'il n'y a pas de pièce sur la case, une référence sur la pièce à la case sélectionnée sinon
     */
    public Piece getPieceOn(BoardPos2D boardPos2D){
        return board[boardPos2D.getX()][boardPos2D.getY()];
    }

    /**
     * Méthode permettant d'obtenir une référence sur une pièce à une position (x,y)
     * @param x position en x de la case
     * @param y position en y de la case
     * @return null s'il n'y a pas de pièce sur la case, une référence sur la pièce à la case sélectionnée sinon
     */
    public Piece getPieceOn(int x, int y){
        return board[x][y];
    }

    /**
     * Méthode permettant de placer une pièce à une position BoardPos2D du board
     * @param piece pièce à placer sur le board
     * @param boardPos2D position dans le board ou la pièce doit être placée
     */
    public void setPieceOn(Piece piece, BoardPos2D boardPos2D){
        board[boardPos2D.getX()][boardPos2D.getY()] = piece;
    }

    /**
     * Méthode permettant de placer une pièce à une position (x, y) du board
     * @param piece pièce à placer sur le board
     * @param x position x de la case où placer la pièce
     * @param y position y de la case où placer la pièce
     */
    public void setPieceOn(Piece piece, int x, int y){
        board[x][y] = piece;
    }
}
