package engine.pieces;

import chess.PieceType;
import chess.PlayerColor;
import engine.Game;
import engine.BoardPos2D;
import engine.moves.Move;

import java.util.List;
import java.util.Objects;

public abstract class Piece {
    protected BoardPos2D m_piecePosition;
    protected final PlayerColor m_pieceColour;

    /**
     * Constructeur du fou
     * @param piecePosition Position ou le fou est placé
     * @param pieceColour Couleur de la pièce
     */
    Piece(final BoardPos2D piecePosition, final PlayerColor pieceColour) {
        m_piecePosition = piecePosition;
        m_pieceColour   = pieceColour;
    }

    /**
     * Redéfinition de la méthode isPieceLegalMove de Piece. Cette méthode permet de tester si le move tenté est légal
     * pour le fou.
     * @param gameState état du reste du jeu
     * @param destination position d'arrivée du déplacement tenté
     * @return Un move en fonction du type de pièce qui y fait appel
     */
    public abstract Move isPieceLegalMove(Game gameState, BoardPos2D destination);

    /**
     * Méthode pour savoir de quel type de pièce il s'agit
     * @return le type de pièce
     */
    public abstract PieceType getPieceName();

    /**
     * Modifie la position de la pièce
     * @param m_piecePosition position BoardPos2D a donner à la pièce
     */
    public void setPiecePosition(BoardPos2D m_piecePosition) {
        this.m_piecePosition = m_piecePosition;
    }

    /**
     * Méthode pour obtenir la couleur de la pièce
     * @return couleur de la pièce
     */
    public PlayerColor getColor(){
        return m_pieceColour;
    }

    /**
     * Méthode pour obtenir la position de la pièce
     * @return position BoardPos2D de la pièce dans le board
     */
    public BoardPos2D getPosition() {
        return m_piecePosition;
    }

    /**
     * Méthode pour obtenir la position en x de la pièce
     * @return position x de la pièce sur le board
     */
    public int getX(){
        return m_piecePosition.getX();
    }

    /**
     * Méthode pour obtenir la position en y de la pièce
     * @return position y de la pièce sur le board
     */
    public int getY(){
        return m_piecePosition.getY();
    }

    // Getters
    // Get next turn magic moves
    // Get next turn legal moves


    /**
     * Redéfinition de la méthode equals afin de permettre de tester l'égalité entre une pièce et un objet
     * L'égalité est confirmé si on test le même objet ou si
     * la position, la couleur et le nom de la pièce sont identiques.
     * @param o Objet dont on veut tester l'égalité
     * @return true si les deux objets sont égaux,
     *         false si ils ne le sont pas
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Piece piece = (Piece) o;
        return Objects.equals(m_piecePosition, piece.m_piecePosition) &&
                m_pieceColour == piece.m_pieceColour &&
                this.getPieceName() == piece.getPieceName();
    }

    /**
     * Redéfinition de la méthode hashcode car on a redéfini la méthode equals
     * @return le hash calculé pour cet objet
     */
    @Override
    public int hashCode() {
        return Objects.hash(m_piecePosition, m_pieceColour);
    }
}
