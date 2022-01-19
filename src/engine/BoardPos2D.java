package engine;

import java.util.Objects;

public class BoardPos2D {
    private final int m_x;
    private final int m_y;

    /**
     * Constructeur de BoardPos2D prenant des coordonées x et y
     * @param x Position X du BoardPos2D
     * @param y Position Y du BoardPos2D
     */
    public BoardPos2D(final int x, final int y){
        m_x = x;
        m_y = y;
    }

    /**
     * Constructeur de BoardPos2D prenant un BoardPos2D en paramètre
     * @param p Position BoardPos2D du BoardPos2D
     */
    public BoardPos2D(final BoardPos2D p){
        m_x = p.getX();
        m_y = p.getY();
    }

    /**
     * Constructeur de BoardPos2D prenant un array de 2 en paramètre
     * @param array Position sous form d'array de 2 du BoardPos2D
     */
    public BoardPos2D(final int[] array){
        assert array.length == 2;

        m_x = array[0];
        m_y = array[1];
    }

    /**
     *
     * @return la position en X du BoardPos2D
     */
    public int getX() {
        return m_x;
    }

    /**
     *
     * @return la position en Y du BoardPos2D
     */
    public int getY() {
        return m_y;
    }

    /**
     * Méthode permettant de déplacer le BoardPos2D d'un offset en x et y.
     * @param offset2D array de taille 2 contenant l'offset dont doit se déplacer le BoardPos2D
     * @return le BoardPos2D après déplacement
     */
    public BoardPos2D offsetBy(final int[] offset2D){
        assert offset2D.length == 2;

        return new BoardPos2D(this.m_x + offset2D[0], this.m_y + offset2D[1]);
    }

    /**
     * Méthode permettant de déplacer le BoardPos2D d'un offset en x et y.
     * @param offset2D array de taille 2 contenant l'offset dont doit se déplacer le BoardPos2D
     * @param factor facteur multiplicatif appliqué à l'offset
     * @return le BoardPos2D après déplacement
     */
    public BoardPos2D offsetBy(final int[] offset2D, int factor){
        assert offset2D.length == 2;

        return new BoardPos2D(this.m_x + factor * offset2D[0], this.m_y + factor * offset2D[1]);
    }

    /**
     * Méthode permettant de checker si la position se trouve dans les limite du plateau de jeu
     * @return true si la position est dans les limites, false sinon
     */
    public boolean isValidPos() {
        return ((0 <= m_x && m_x < 8) && (0 <= m_y && m_y < 8));
    }

    /**
     * Redéfinition de la méthode equals afin de permettre de tester l'égalité des valeurs internes entre deux BoardPos2D
     * @param o Objet dont on veut tester l'égalité
     * @return true si les deux objets sont égaux,
     *         false si ils ne le sont pas
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BoardPos2D that = (BoardPos2D) o;
        return m_x == that.m_x && m_y == that.m_y;
    }

    /**
     * Redéfinition de la méthode hashcode car on a redéfini la méthode equals
     * @return le hash calculé pour cet objet
     */
    @Override
    public int hashCode() {
        return Objects.hash(m_x, m_y);
    }
}
