package engine;

import chess.ChessView;
import chess.PieceType;
import chess.PlayerColor;
import engine.moves.Move;
import engine.moves.PawnJump;
import engine.pieces.*;

public class Game implements chess.ChessController
{
    // board
    private Board board;

    // view
    private ChessView view;

    // turn
    private PlayerColor playerTurn;

    private final Promotion[] listePromotion =
            {
                    new Promotion("Queen"),
                    new Promotion("Knight"),
                    new Promotion("Rook"),
                    new Promotion("Bishop")
            };

    // players
    private final Player[] players =
            {
                    new Player(PlayerColor.WHITE,  1),
                    new Player(PlayerColor.BLACK, -1)
            };

    private BoardPos2D enPassant;


    /**
     * Démarre la logique (contrôleur) du programme.
     * Appelé une fois (voir Chess.main())
     *
     * @param view la vue à utiliser
     */
    @Override
    public void start(ChessView view) {
        this.view = view;
        this.view.startView();

        newGame();
    }

    /**
     * Appelé lorsque l'utilisateur a demandé un déplacement de la position X à la position Y.
     * La position 0, 0 est en bas à gauche de l'échiquier.
     *
     * @param fromX La position x de départ de la pièce
     * @param fromY La position y de départ de la pièce
     * @param toX La position x d'arrivée de la pièce
     * @param toY La position y d'arrivée de la pièce
     * @return true si le mouvement a pu avoir lieu, false dans le cas contraire.
     */
    @Override
    public boolean move(int fromX, int fromY, int toX, int toY) {
        Piece pieceToMove = board.getPieceOn(fromX, fromY);

        // Vérifie si on a cliqué sur une case vide
        if (pieceToMove == null) {
            return false;
        }

        // Vérifie si la pièce qu'on veut déplacer est de la bonne couleur
        if (pieceToMove.getColor() != playerTurn) {
            return false;
        }

        // Vérifie que la case d'arrivée est une case occupable par cette pièce (vide ou avec une pièce adverse)
        if (board.getPieceOn(toX, toY) != null && pieceToMove.getColor() == board.getPieceOn(toX, toY).getColor()) {
            return false;
        }

        Move move = pieceToMove.isPieceLegalMove(this, new BoardPos2D(new int[]{toX, toY}));

        // Vérifie que le move a été accepter par la pièce
        if (move == null) {
            return false;
        }

        // Vérifie que le roi ne soit pas mis en échec par le mouvement.
        if(!move.doMove()){
            return false;
        }

        if(!(move instanceof PawnJump)){
            enPassant = null;
        }

        view.displayMessage("");

        if(pieceToMove.getPieceName() == PieceType.PAWN){
            if((pieceToMove.getY() == 0 && pieceToMove.getColor() == PlayerColor.BLACK) ||
               (pieceToMove.getY() == 7 && pieceToMove.getColor() == PlayerColor.WHITE)){
                promotion(pieceToMove);
            }
        }

        if(getCurrentPayer().canAttack(this, getOtherPlayer().getKing().getPosition()))
            view.displayMessage("Roi " + this.getOtherPlayer().getColor() + " check");

        nextTurn();

        return true;
    }

    /**
     * Démarre une nouvelle partie. L'échiquier doit être remis dans sa position initiale.
     */
    @Override
    public void newGame() {
        board = new Board();
        playerTurn = PlayerColor.WHITE;

        for (Player player : players) {
            player.resetPlayer();

            for (Piece piece : player.getPieces())
                placePiece(piece);
        }
    }

    /**
     * Change le joueur courant (change le joueur qui peut jouer)
     */
    public void nextTurn(){
        this.playerTurn = PlayerColor.WHITE == this.playerTurn ? PlayerColor.BLACK : PlayerColor.WHITE;
    }

    /**
     *
     * @return retourne le joueur courant (dont c'est le tour)
     */
    public Player getCurrentPayer() {
        return playerTurn == players[0].getColor() ? players[0] : players[1];
    }

    /**
     *
     * @return Retourne le joueur dont ce n'est pas le tour
     */
    public Player getOtherPlayer() {
        return playerTurn == players[0].getColor() ? players[1] : players[0];
    }

    /**
     * Méthode appelée lors du placement d'une pièce sur le board
     * @param piece Pièce que l'on souhaite placer sur le board.
     */
    public void placePiece(Piece piece){
        this.board.setPieceOn(piece, piece.getPosition());
        this.view.putPiece(piece.getPieceName(), piece.getColor(), piece.getX(), piece.getY());
    }

    /**
     * Méthode utilisée lorsqu'une piece du joueur courant mange une pièce adverse. Permet de retirer la pièce de la
     * table de pièce du joueur.
     * @param piece Piece que l'on souhaite retirer du jeu
     */
    public void killPiece(Piece piece){
        getOtherPlayer().removePiece(piece);
    }

    /**
     * Méthode utilisée pour déplacer une pièce d'un point à un autre
     * @param from Position de départ de la pièce
     * @param to Position d'arrivée de la pièce
     */
    public void movePiece(BoardPos2D from, BoardPos2D to) {
        Piece pieceToMove = getPieceOn(from);

        view.putPiece(pieceToMove.getPieceName(), pieceToMove.getColor(), to.getX(), to.getY());
        view.removePiece(from.getX(), from.getY());

        board.setPieceOn(pieceToMove, to);
        board.setPieceOn(null, from);
    }

    /**
     * Méthode utilisée pour déplacer une pièce d'un point à un autre
     * @param to pièce contenant sa position d'arrivée sur le board
     * @param from position d'ou est partie la pièce
     */
    public void movePiece(Piece to, BoardPos2D from) {

        view.removePiece(from.getX(), from.getY());
        view.putPiece(to.getPieceName(), to.getColor(), to.getX(), to.getY());
    }

    /**
     * Méthode pour récupérer une référence sur la pièce en position pos2D sur le board
     * @param pos2D Position dont on aimerait récupérer la pièce (type BoardPos2D)
     * @return Si la case est vide, retourne null, sinon, retourne une référence sur
     *         la pièce actuellement sur cette case.
     */
    public Piece getPieceOn(BoardPos2D pos2D){
        return board.getPieceOn(pos2D);
    }

    /**
     * Méthode pour récupérer une référence sur la pièce en position x, y sur le board
     * @param x Position en x de la case
     * @param y Position en y de la case
     * @return null si la case en (x, y) est vide, sinon, retourne une référence sur
     *         la pièce actuellement sur cette case.
     */
    public Piece getPieceOn(int x, int y){
        return board.getPieceOn(x, y);
    }

    /**
     * Méthode utilisée pour définir une position permettant une prise en passant en indiquant qu'une case est prenable
     * par un en passant.
     * @param positionEnPassant Position pour la prise en passant (Doit toujours se trouver juste derrière un pion)
     */
    public void setPositionEnPassant(BoardPos2D positionEnPassant) {
        this.enPassant  = positionEnPassant;
    }

    /**
     * Méthode pour récupérer la position du en passant s'il y en a une
     * @return null s'il n'y a aucune position d'en passant,
     *         Position du en passant
     */
    public BoardPos2D getPositionEnPassant() {
        return enPassant;
    }

    /**
     * Méthode de prise en passant, supprime la pièce  qui se trouve une case plus bas
     * (par rapport à la pièce qui mange).
     */
    public void removeEnPassantPawn() {
        if (enPassant.getY() == 2) {
            view.removePiece(enPassant.getX(), 3);
            board.setPieceOn(null, enPassant.getX(), 3);
        }

        if (enPassant.getY() == 5) {
            view.removePiece(enPassant.getX(), 4);
            board.setPieceOn(null, enPassant.getX(), 4);
        }
    }

    /**
     * Méthode pour promouvoir un pion. Demande à l'utilisateur de choisir une pièce en laquelle il souhaite promouvoir
     * son pion
     * @param pawnToPromote Pion que l'on souhaite promouvoir.
     */
    private void promotion(Piece pawnToPromote){

        Promotion tmp = view.askUser("Promotion", "Choisissez la pièce à promouvoir", listePromotion[0],
                                                                                                   listePromotion[1],
                                                                                                   listePromotion[2],
                                                                                                   listePromotion[3]);

        Piece pieceToAdd = null;
        switch(tmp.toString()){
            case "Queen":
                pieceToAdd = new Queen(pawnToPromote.getPosition(), pawnToPromote.getColor());
                break;

            case "Knight":
                pieceToAdd = new Knight(pawnToPromote.getPosition(), pawnToPromote.getColor());
                break;

            case "Rook":
                pieceToAdd = new Rook(pawnToPromote.getPosition(), pawnToPromote.getColor());
                break;

            case "Bishop":
                pieceToAdd = new Bishop(pawnToPromote.getPosition(), pawnToPromote.getColor());
                break;
        }

        killPiece(pawnToPromote);
        placePiece(pieceToAdd);

    }

    /**
     * Méthode utilisée pour placer une pièce sur le board à traver Game
     * @param pieceToMove pièce que l'on souhaite placer sur le board
     * @param destination position où l'on souhaite poser la pièce
     */
    public void movePieceBoard(Piece pieceToMove, BoardPos2D destination){
        board.setPieceOn(pieceToMove, destination);
    }


}
