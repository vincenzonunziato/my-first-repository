package tictactoe;

/**
 * @brief Interfaccia comune al giocatore umano e al
 * giocatore computerizzato.
 */
public abstract class Player {
    private int mark;
    
    /**
     * @brief Costruttore
     * @param[in] mark Simbolo con cui è rappresentato il
     *                   giocatore. Deve essere Board.CELL_X oppure
     *                   Board.CELL_O
     */
    protected Player(int mark) {
        assert mark==Board.CELL_X || mark==Board.CELL_O;
        this.mark=mark;
    }
    
    /**
     * @brief Restituisce il simbolo con cui è rappresentato il
     * giocatore.
     * @return Board.CELL_X oppure Board.CELL_O
     */
    public int getMark() {
        return mark;
    }

    
    /**
     * @brief Sceglie la mossa del giocatore.
     * @param[in] board La scacchiera corrente.
     * @return La mossa scelta, tra 0 e Board.SIZE.
     * @pre 
     * La scacchiera corrente non indica una partita
     * terminata (vedi Board#isFinished).
     * @post
     * La mossa restituita è valida (vedi Board#isValidMove).
     */
    protected abstract int chooseMove(Board board); 
    
    /**
     * @brief Il giocatore sceglie ed esegue una mossa.
     * @param[inout] board La scacchiera corrente.
     * @pre 
     * La scacchiera corrente non indica una partita
     * terminata (vedi Board#isEnded).
     * @post
     * La scacchiera è modificata in base alla mossa del
     * giocatore.
     */
    public void makeMove(Board board) {
        int move=chooseMove(board);
        board.applyMove(move);
    } 
    
    /**
     * @brief Informa il giocatore dell'esito della partita.
     * @param[in] winner Il mark del vincitore (Board.CELL_X oppure
     *                  Board.CELL_O) in caso di vittoria; il valore
     *                  Board.CELL_NONE in caso di pareggio.
     */
    public void notifyResult(int winner) {
        // Per default, non fa nulla.
    }
}
