package tictactoe;
import java.util.Scanner;
/**
 * @brief L'interfaccia utente
 */
public class UserInterface {
    private Scanner scanner;
    
    /**
     * @brief Costruttore di default.
     */
    public UserInterface() {
        scanner=new Scanner(System.in);
    }
    
    /**
     * @brief Chiede all'utente con quale simbolo vuole giocare.
     * @return Board.CELL_X oppure Board.CELL_O
     */
    public int askMark() {
        System.out.println("Scegli il simbolo con cui giocherai:");
        while (true) {
            System.out.print("Inserisci 1 per 'X' o 2 per 'O' : ");
            int scelta=scanner.nextInt();
            if (scelta==1)
                return Board.CELL_X;
            else if (scelta==2)
                return Board.CELL_O;
        }
    }
    
    /**
     * @brief Chiede all'utente il livello di difficoltà desiderato.
     * @param[in] maxLevel Il massimo livello di difficoltà (>= 0)
     * @return Un valore compreso tra 0 e maxLevel (incluso).
     */
    public int askDifficultyLevel(int maxLevel) {
        assert maxLevel>=0;
        System.out.println("Scegli il livello di difficoltà:");
        while (true) {
            System.out.print("Inserisci un numero tra 0 e "+
                    maxLevel +": ");
            int scelta=scanner.nextInt();
            if (scelta>=0 && scelta<=maxLevel)
                return scelta;
        }
    }

    /**
     * @brief Visualizza lo stato della scacchiera.
     * @param[in] board La scacchiera.
     */
    public void showBoard(Board board) {
        System.out.println();
        for(int row=0; row<3; row++) {
            for(int col=0; col<3; col++) {
                int pos=row*3+col;
                int cell=board.getCellContent(pos);
                System.out.print(
                        cell==Board.CELL_X? " X ":
                                cell==Board.CELL_O? " O ":
                                        " "+(pos+1)+" "
                );
                if (col<2)
                    System.out.print("|");
            }
            System.out.println();
            if (row<2)
                System.out.println("---+---+---");
        }
        
        if (board.isEnded()) {
            System.out.println("La partita è finita. ");
        } else {
            String next=board.getNextMark()==Board.CELL_X? "X": "O";
            System.out.println("Deve muovere: "+next);
        }
    
    }
    
    /**
     * @brief Annuncia il risultato della partita.
     * @param[in] message Messaggio da visualizzare, che
     *         descrive il risultato.
     */
    public void showResult(String message) {
        System.out.println(message);
    }
    
    /**
     * @brief Chiede all'utente la mossa scelta.
     * @param[in] board La scacchiera.
     * @pre
     * La partita non è finita (vedi Board#isFinished).
     * @return La mossa scelta, tra 0 e Board.SIZE.
     * @post La mossa restituita è valida (vedi Board#isValidMove).
     */
    public int askMove(Board board) {
        assert !board.isEnded();
        while (true) {
            System.out.print("Scegli la tua mossa: ");
            int scelta=scanner.nextInt()-1;
            if (scelta>=0 && scelta<Board.SIZE &&
                board.isValidMove(scelta))
                return scelta;
        }

    }
    
    /**
     * @brief Chiede all'utente se vuole giocare ancora.
     * @return true se l'utente vuole giocare ancora
     */
    public boolean askPlayAgain() {
        System.out.println("Vuoi giocare ancora?");
        while (true) {
            System.out.print("Inserisci 1 per Sì oppure 2 per No: ");
            int scelta=scanner.nextInt();
            if (scelta==1)
                return true;
            else if (scelta==2)
                return false;
        }
    }
}
