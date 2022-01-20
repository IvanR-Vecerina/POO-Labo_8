package engine.moves;

import chess.PieceType;
import engine.Game;
import engine.BoardPos2D;
import engine.pieces.King;
import engine.pieces.MoveTrackedPiece;
import engine.pieces.Pawn;
import engine.pieces.Piece;

/**
 * Classe Move, permettant de tester si le movement accepté par la pièce ne met pas son roi en échec, puis effectue
 * le déplacement de la pièce.
 *
 * @author Ivan Vecerina
 * @author Thibault Seem
 */
public class Move {
    protected final Game gameState;
    protected final Piece pieceToMove;
    protected final BoardPos2D destination;
    protected BoardPos2D posInit;

    /**
     * Constructeur d'un move
     * @param gameState état actuel du jeu
     * @param pieceToMove pièce qui doit être déplacée
     * @param destination position ou la pièce doit être déplacée
     */
    public Move(Game gameState, Piece pieceToMove, BoardPos2D destination) {
        this.gameState = gameState;
        this.pieceToMove = pieceToMove;
        this.destination = destination;
    }

    /**
     * Méthode permettant de vérifier si un déplacement ne viol pas des conditions spécifiques avant d'appliquer ou non
     * le dit déplacement à la pièce et au jeu.
     * @return retourne true si le déplacement a pu être effectué
     *         retourne false si le déplacement n'a pas pu être effectué
     */
    public boolean doMove(){
        tryMove();
        if(checkMove()){
            execute();
            return true;
        }else{
            rollbackMove();
            return false;
        }
    }

    /**
     * Méthode utilisée pour essayer de déplacer la pièce à son point d'arrivée. Nous sauvegardons sa position
     * de départ au cas ou le mouvement ne respecte pas les contraintes du moves.
     */
    protected void tryMove(){
        posInit = pieceToMove.getPosition();

        gameState.movePieceBoard(pieceToMove, destination);
        gameState.movePieceBoard(null, pieceToMove.getPosition());
        pieceToMove.setPiecePosition(destination);
    }

    /**
     * Méthode utilisée pour vérifier que le déplacement de la pièce ne viol aucune contrainte
     * @return retourne true si aucune contrainte n'a été violée
     *         retourne false si un viol de contrainte a été remarqué
     */
    protected boolean checkMove(){
        if(gameState.getOtherPlayer().canAttack(gameState, gameState.getCurrentPayer().getKing().getPosition())){
            return false;
        }else{
            return true;
        }
    }

    /**
     * Méthode utilisée pour remettre le board dans l'état précédent les test de vérification.
     * Elle est appelée lorsqu'une contrainte est violée.
     */
    protected void rollbackMove() {
        gameState.movePieceBoard(pieceToMove, posInit);
        gameState.movePieceBoard(null, pieceToMove.getPosition());
        pieceToMove.setPiecePosition(posInit);
    }

    /**
     * Méthode utilisée pour appeler l'affichage des pièces et vérifier que les différentes valeurs liées au premier
     * mouvement d'une pièce soit bien modifiée après un déplacement.
     */
    protected void execute() {
        gameState.movePiece(pieceToMove, posInit);

        if (pieceToMove.getPieceName() == PieceType.PAWN){
            ((Pawn) pieceToMove).hasMoved();
        }else if(pieceToMove.getPieceName() == PieceType.KING){
            ((King) pieceToMove).hasMoved();
            gameState.getCurrentPayer().removeCastlelingL();
            gameState.getCurrentPayer().removeCastlelingR();
        }else if(pieceToMove.getPieceName() == PieceType.ROOK){
            if(posInit.getX() == 7){
                gameState.getCurrentPayer().removeCastlelingR();
            }else if(posInit.getX() == 0){
                gameState.getCurrentPayer().removeCastlelingL();
            }
        }
    }

    /**
     * Getter pour récupérer la destination à laquelle doit se déplacer la pièce
     * @return Position ou doit se déplacer la pièce
     */
    public BoardPos2D getDestination() {
        return destination;
    }
}
