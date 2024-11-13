package tictactoe;

/**
 * @brief La scacchiera del gioco.
 * @invariant
 * Il numero di celle contenenti X è uguale
 * al numero di celle contenenti O, oppure
 * al numero di celle contenenti O più 1.
 * @invariant
 * Se il numero di X è uguale al numero di O,
 * il prossimo giocatore è il giocatore X;
 * altrimenti il prossimo giocatore è O.
 */
public class Board {
    /**
     * @brief Costante usata per indicare che una cella è vuota.
     */
    public static final int CELL_NONE=0;
    /**
     * @brief Costante usata per indicare che una cella contiene
     *        il mark X.
     */
    public static final int CELL_X=1;
    /**
     * @brief Costante usata per indicare che una cella contiene il
     *        mark O.
     */
    public static final int CELL_O=2;
    
    /**
     * @brief Dimensione della scacchiera (numero di celle)
     */
    public static final int SIZE=9;
    
    private int cell[];
    private int next;
    private boolean ended;
    private int winner;
    
    /**
     * @brief Costruttore di default.
     * @post
     * La scacchiera è vuota.
     */
    public Board() {
        cell=new int[SIZE];
        for(int i=0; i<SIZE; i++)
            cell[i]=CELL_NONE;
        next=CELL_X;
        ended=false;
        winner=CELL_NONE;
    }
    
    /**
     * @brief Costruttore di copia
     * @param[in] other La scacchiera da copiare
     * @post
     * La scacchiera creata è identica a other.
     */
    public Board(Board other) {
        cell=new int[SIZE];
        for(int i=0; i<SIZE; i++)
            cell[i]=other.cell[i];
        next=other.next;
        ended=other.ended;
        winner=other.winner;
    }
    
    /**
     * @brief Restituisce il mark del prossimo giocatore che deve muovere.
     * @return La costante CELL_X oppure CELL_O.
     * @pre 
     * La partita non è terminata (vedi #isEnded)
     */
    public int getNextMark() {
        assert !ended;
        return next;
    }
    
    /**
     * @brief Restituisce il contenuto di una cella.
     * @param[in] position Posizione della cella, tra 0 e SIZE-1
     * @return CELL_NONE, CELL_X oppure CELL_O.
     */
    public int getCellContent(int position) {
        return cell[position];
    }
    
    /**
     * @brief Verifica se una mossa è valida.
     * @param[in] move mossa da verificare, tra 0 e SIZE-1
     * @return true se la mossa è valida
     */
    public boolean isValidMove(int move) {
        return cell[move]==CELL_NONE;
    }

    /**
     * @brief Modifica la scacchiera eseguendo una mossa
     * @param[in] move mossa da eseguire, tra 0 e SIZE-1
     * @pre 
     * La mossa specificata è valida
     * @post
     * La cella corrispondente alla mossa è occupata dal giocatore
     * che deve muovere.
     * @post
     * Il prossimo giocatore che deve muovere è cambiato da X a O oppura
     * da O a X.
     * @post
     * Viene controllato se al termine della mossa la partita è
     * finita (vedi #isEnded) e in tal caso chi è il
     * vincitore (vedi #getWinner).
     */
    public void applyMove(int move) {
        assert cell[move]==CELL_NONE;
        cell[move]=next;
        next=getOpponentMark(next);
        checkIfEnded();
    }
    
    /**
     * @brief Verifica se la scacchiera indica che la partita
     * è terminata.
     * @return true se la partita è terminata
     */
    public boolean isEnded() {
        return ended;
    }
    
    /**
     * @brief Restituisce il mark del vincitore se la 
     *        partita è terminata.
     * @pre
     * La partita è terminata.
     * @return CELL_X o CELL_O se uno dei due giocatori ha vinto;
     *         CELL_NONE in caso di pareggio.
     */
    public int getWinner() {
        assert ended;
        return winner;
    }
    
    /**
     * @brief  Dato il mark di un giocatore, restituisce
     *         il mark del suo avversario.
     * @param[in] mark Il simbolo del giocatore.
     * @return Se mark è CELL_X restituisce CELL_O, e
     *         viceversa.
     * @pre mark è CELL_X oppure CELL_O
     */
    public static int getOpponentMark(int mark) {
        assert mark==CELL_X || mark==CELL_O;
        return mark==CELL_X? CELL_O: CELL_X;
    }
    
    /**
     * @brief Controlla se la partita è terminata.
     */
    private void checkIfEnded() {
        if (ended)
            return;
        for(int i=0; i<tris.length; i++) {
            int nx=0;
            int no=0;
            for(int j=0; j<3; j++) {
                int c=cell[tris[i][j]];
                if (c==CELL_X)
                    nx++;
                else if (c==CELL_O)
                    no++;
            }
            if (nx==3) {
                winner=CELL_X;
                ended=true;
                return;
            } else if (no==3) {
                winner=CELL_O;
                ended=true;
                return;
            }
        }
        int nn=0;
        for(int i=0; i<SIZE; i++)
            if (cell[i]==CELL_NONE)
                nn++;
        if (nn==0)
            ended=true;
    }
    
    private static final int[][] tris={
        {0, 1, 2},
        {3, 4, 5},
        {6, 7, 8},
        {0, 3, 6},
        {1, 4, 7},
        {2, 5, 8},
        {0, 4, 8},
        {6, 4, 2}
    };
    
    
}
