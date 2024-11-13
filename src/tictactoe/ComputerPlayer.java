package tictactoe;
import java.util.Random;

/**
 * @brief Giocatore computerizzato.
 */
public class ComputerPlayer extends Player {
    /**
     * @brief Livello massimo di difficoltà.
     */
    public static int MAX_LEVEL=8;

    private int level;
    private Random random;
    
    /**
     * @brief Costruttore.
     * @param[in] symbol Simbolo con cui è rappresentato il giocatore;
     *            deve essere Board.CELL_X oppure Board.CELL_O
     * @param[in] level Livello di difficoltà, compreso tra 0 e MAX_LEVEL.
     */
    public ComputerPlayer(int symbol, int level) {
        super(symbol);
        assert level>=0 && level<=MAX_LEVEL;
        this.level=level;
        random=new Random();
    }
    

    private static final int
            VALUE_WIN=100000,
            VALUE_NEUTRAL=100;
    
    /**
     * @brief Sceglie la mossa del computer.
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
        assert !board.isEnded();
        int bestMove=-1;
        int bestValue=Integer.MIN_VALUE;
        for(int move=0; move<Board.SIZE; move++) {
            if (board.isValidMove(move)) {
                int value=evaluateMove(board, move, level);
                if (value>bestValue) {
                    bestMove=move;
                    bestValue=value;
                }
            }
        }
        assert bestMove>=0;
        return bestMove;
    }
    
    /**
     * @brief Calcola il valore di una mossa.
     * 
     * Se lookAhead è 0, non considera le conseguenze
     * della mossa, ma restituisce un valore casuale tra 0 e
     * VALUE_NEUTRAL-1.
     * Altrimenti, guarda la scacchiera risultante dall'applicazione
     * della mossa:
     * - se la partita è terminata e il giocatore che ha mosso vince,
     *   restituisce VALUE_WIN (un valore molto alto)
     * - altrimenti, se la partita è terminata in pareggio, restituisce 
     *   un valore casuale tra 0 e VALUE_NEUTRAL-1
     * - altrimenti (la partita non è terminata), esamina tutte le
     *   possibili contromosse dell'avversario, e restituisce il valore
     *   corrispondente alla contromossa peggiore (ovvero, migliore
     *   per l'avversario). Una contromossa è valutata usando una
     *   chiamata ricorsiva, con lookAhead diminuito di 1, e
     *   cambiando il segno del valore restituito (per tenere conto
     *   del fatto che una mossa buona per l'avversario è cattiva
     *   per il giocatore, e viceversa).
     * 
     * @param[in] lookAhead Il numero di turni di gioco da considerare
     *            nella valutazione.
     * 
     */
    private int evaluateMove(Board board, int move, int lookAhead) {
        if (lookAhead==0)
            return random.nextInt(VALUE_NEUTRAL);
        Board copy=new Board(board);
        copy.applyMove(move);
        if (copy.isEnded()) {
            if (copy.getWinner()==Board.CELL_NONE)
                return random.nextInt(VALUE_NEUTRAL);
            else
                return VALUE_WIN;
        } else {
            int worstValue=Integer.MAX_VALUE;
            for(int counterMove=0; counterMove<Board.SIZE; counterMove++)
                if (copy.isValidMove(counterMove)) {
                    int value= - evaluateMove(copy, counterMove, lookAhead-1);
                    if (value<worstValue)
                        worstValue=value;
                }
            return worstValue;
        }
    }
}
