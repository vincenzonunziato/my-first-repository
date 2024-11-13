package tictactoe;

/**
 * @brief Una partita del gioco.
 */
public class Game {
    private Player player[];
    private Board board;
    private UserInterface userInterface;
    
    /**
     * @brief Costruttore.
     * @param[in] userInterface La user interface.
     */
    public Game(UserInterface userInterface) {
        this.userInterface=userInterface;
        player=new Player[2];
        board=new Board();
        int humanSymbol=userInterface.askMark();
        player[0]=new HumanPlayer(humanSymbol, userInterface);
        int computerSymbol=Board.getOpponentMark(humanSymbol);
        int computerLevel=userInterface.askDifficultyLevel(ComputerPlayer.MAX_LEVEL);
        player[1]=new ComputerPlayer(computerSymbol, computerLevel);  
    }
    
    /**
     * @brief Gioca la partita.
     */
    public void play() {
        while (!board.isEnded()) {
            userInterface.showBoard(board);
            Player next=findNextPlayer();
            next.makeMove(board);
        }
        userInterface.showBoard(board);
        int winner=board.getWinner();
        for(Player p: player)
            p.notifyResult(winner);
    }
    
    /**
     * @brief Individua il player che deve muovere.
     * @pre
     * La partita non è terminata
     * @return Il player che deve muovere.
     */
    private Player findNextPlayer() {
        if (board.getNextMark()==player[0].getMark())
                return player[0];
        else
                return player[1];
    }
    
    public static void main(String args[]) {
        UserInterface ui=new UserInterface();
        do {
            Game g=new Game(ui);
            g.play();
        } while (ui.askPlayAgain());
    }
}
