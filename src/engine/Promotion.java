package engine;

import chess.ChessView;

/**
 * Classe Promotion, classe permettant implémenter ChessView.UserChoice afin de pouvoir afficher des propositions lors
 * d'une demande de promotion
 *
 * @author Ivan Vecerina
 * @author Thibault Seem
 */
public class Promotion implements ChessView.UserChoice {
    private final String name;

    /**
     * Constructeur de la classe promotion. Stocke simplement un string correspondant à la proposition à faire à
     * l'utilisateur lors de la demande de choix.
     * @param PieceName Nom de la pièce à laquelle correspond cet objet
     */
    public Promotion(String PieceName) {
        this.name = PieceName;
    }

    /**
     *
     * @return un string contenant le nom de la pièce à laquelle correspond cet objet
     */
    @Override
    public String toString() {
        return name;
    }

    /**
     * Permet d'ajouter du test à la fin du text de choix
     * @return null tout le temps, nous n'avons pas d'indication supplémentatire à donner.
     */
    @Override
    public String textValue() {
        return null;
    }
}
