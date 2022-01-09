package engine;

import chess.ChessView;

public class Game implements chess.ChessController
{
    // Board
    // 2 Pieces sets
    // Game variables


    /**
     * Démarre la logique (contrôleur) du programme.
     * Appelé une fois (voir Chess.main())
     *
     * @param view la vue à utiliser
     */
    @Override
    public void start(ChessView view) {
        view.startView();
    }

    /**
     * Appelé lorsque l'utilisateur a demandé un déplacement de la position X à la position Y.
     * La position 0, 0 est en bas à gauche de l'échiquier.
     *
     * @param fromX
     * @param fromY
     * @param toX
     * @param toY
     * @return true si le mouvement a pu avoir lieu, false dans le cas contraire.
     */
    @Override
    public boolean move(int fromX, int fromY, int toX, int toY) {
        System.out.println("(" + fromX + ", " + fromY + ") ---> (" + toX + ", " + toY + ")");
        return false;
    }

    /**
     * Démarre une nouvelle partie. L'échiquier doit être remis dans sa position initiale.
     */
    @Override
    public void newGame() {

    }
}
