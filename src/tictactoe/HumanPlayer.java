package tictactoe;


/**
 * @brief Il giocatore umano
 */
public class HumanPlayer extends Player {
    private UserInterface userInterface;

        
    /**
     * @brief Costruttore.
     * @param[in] symbol Simbolo con cui è rappresentato il giocatore;
     *            deve essere Board.CELL_X oppure Board.CELL_O
     * @param[in] userInterface L'interfaccia utente
     */
    public HumanPlayer(int symbol, UserInterface userInterface) {
        super(symbol);
        this.userInterface=userInterface;
    }
 
    
    
    /**
     * @brief Sceglie la mossa del giocatore umano.
     * @param[in] board La scacchiera corrente.
     * @return La mossa scelta, tra 0 e Board.SIZE.
     * @pre 
     * La scacchiera corrente non indica una partita
     * terminata (vedi Board#isFinished).
     * @post
     * La mossa restituita è valida (vedi Board#isValidMove).
     */
    @Override
    protected int chooseMove(Board board) {
        return userInterface.askMove(board);
    }

    /**
     * @brief Informa il giocatore umano dell'esito della partita.
     * @param[in] winner Il simbolo del vincitore (Board.CELL_X oppure
     *                  Board.CELL_O) in caso di vittoria; il valore
     *                  Board.CELL_NONE in caso di pareggio.
     */
    @Override
    public void notifyResult(int winner) {
        String message;
        if (winner==Board.CELL_NONE)
            message="È finita in parità. "+
                    "Il prossimo match sarà decisivo!";
        else if (winner==getMark())
            message="Hai vinto! Sei un vero campione!";
        else
            message="Hai perso, ma non abbatterti.\n"+
                    "La prossima volta andrà sicuramente meglio!";
        userInterface.showResult(message);
    }
    
}
