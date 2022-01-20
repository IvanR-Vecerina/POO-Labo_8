package engine.moves;

import engine.Game;
import engine.BoardPos2D;
import engine.pieces.Piece;

public class Castling extends Move {
    Piece m_rookToCastle;
    boolean rookCanMove;
    BoardPos2D posRook;
    int rookMove, kingMove;

    /**
     * Constructeur d'un move PawnJump
     * @param gameState état actuel du jeu
     * @param pieceToMove pièce qui doit être déplacée
     * @param destination position ou la pièce doit être déplacée
     * @param rookToCastle tour utilisée pour roquer
     */
    public Castling(Game gameState, Piece pieceToMove, BoardPos2D destination, Piece rookToCastle) {
        super(gameState, pieceToMove, destination);
        m_rookToCastle = rookToCastle;
    }

    /**
     * Méthode utilisée ici pour préparer les différentes positions nécessaire pour checker si un roque est possible
     */
    @Override
    protected void tryMove() {
        posInit = pieceToMove.getPosition();
        posRook = m_rookToCastle.getPosition();

        if (m_rookToCastle.getX() == 7){
            rookMove = -2;
            kingMove = 1;
        }else if (m_rookToCastle.getX() == 0){
            rookMove = 3;
            kingMove = -1;
        }
    }

    /**
     * Méthode utilisée pour vérifier que le déplacement de la pièce ne viol aucune contrainte
     * @return retourne true si aucune contrainte n'a été violée
     *         retourne false si un viol de contrainte a été remarqué
     */
    @Override
    protected boolean checkMove() {

        if(m_rookToCastle.getX() == 7 && gameState.getCurrentPayer().isPlayerRCastlable() ||
           m_rookToCastle.getX() == 0 && gameState.getCurrentPayer().isPlayerLCastlable()){

            BoardPos2D p;
            for(int i = 0; i < 3; i++){
                p = new BoardPos2D((i * kingMove) + pieceToMove.getX(), pieceToMove.getY());
                if(gameState.getOtherPlayer().canAttack(gameState, p)){
                    return false;
                }
            }

            if(m_rookToCastle.isPieceLegalMove(gameState, m_rookToCastle.getPosition().offsetBy(new int[]{rookMove, 0})).doMove()){
                return true;
            }
        }
        return false;
    }

    /**
     * Méthode utilisée pour remettre le board dans l'état précédent les test de vérification.
     * Elle est appelée lorsqu'une contrainte est violée.
     */
    @Override
    protected void rollbackMove() {
        BoardPos2D tmp = m_rookToCastle.getPosition();
        gameState.movePieceBoard(null, m_rookToCastle.getPosition());
        gameState.movePieceBoard(m_rookToCastle, posRook);
        m_rookToCastle.setPiecePosition(posRook);
        gameState.movePiece(m_rookToCastle, tmp);


    }

    /**
     * Méthode utilisée pour appeler l'affichage des pièces et vérifier que les différentes valeurs liées au premier
     * mouvement d'une pièce soit bien modifiée après un déplacement.
     */
    @Override
    public void execute() {

        pieceToMove.setPiecePosition(destination);
        super.execute();

    }
}
