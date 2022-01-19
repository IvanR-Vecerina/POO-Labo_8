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
     * @param
     */
    public BoardPos2D(final BoardPos2D p){
        m_x = p.getX();
        m_y = p.getY();
    }

    public BoardPos2D(final int[] array){
        assert array.length == 2;

        m_x = array[0];
        m_y = array[1];
    }

    public int getX() {
        return m_x;
    }

    public int getY() {
        return m_y;
    }

    public BoardPos2D offsetBy(final int[] offset2D){
        assert offset2D.length == 2;

        return new BoardPos2D(this.m_x + offset2D[0], this.m_y + offset2D[1]);
    }

    public BoardPos2D offsetBy(final int[] offset2D, int factor){
        assert offset2D.length == 2;

        return new BoardPos2D(this.m_x + factor * offset2D[0], this.m_y + factor * offset2D[1]);
    }

    public boolean isValidPos() {
        return ((0 <= m_x && m_x < 8) && (0 <= m_y && m_y < 8));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BoardPos2D that = (BoardPos2D) o;
        return m_x == that.m_x && m_y == that.m_y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(m_x, m_y);
    }
}
