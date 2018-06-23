import java.io.IOException;
import java.util.ArrayList;

public class Game extends Board {
    private ArrayList<Player> players;
    private Integer currentplayer;
    public Bag bag;
    public final GameWindow gameWindow;
    public Tree tree = Tree.getInstance();
    public ArrayList<Move> history;

    public Game(GameWindow gameWindow, ArrayList<Player> players) throws IOException {
        super();
        this.currentplayer = 0;
        this.players = players;
        this.bag = new Bag();
        this.bag.loadPolishScrabble();
        this.gameWindow = gameWindow;
        this.history = new ArrayList<>();
    }

    public Player getCurrentplayer() {
        return players.get(currentplayer);
    }

    public void addmove(Move move) {
        System.out.println(move);
        history.add(move);
        gameWindow.getHistoryPanel().getListModel().addElement(move);
        this.tiles.addAll(move.tiles);
        for (Tile t : move.tiles) {
            this.board[t.column][t.row] = t.letter;
        }
        move.player.score += move.score;
        currentplayer = (currentplayer + 1) % players.size();
        gameWindow.repaintChildren();
    }

    public void deletelastmove() {
        if(history.size()<=0)
            return;
        Move last = history.get(history.size() - 1);
        gameWindow.getHistoryPanel().getListModel().removeElement(last);
        history.remove(history.size() - 1);
        for (Tile t : last.tiles) {
            this.board[t.column][t.row] = null;
            this.tiles.remove(t);
        }
        last.player.score -= last.score;
        currentplayer = (currentplayer -1 + players.size()) % players.size();
        gameWindow.repaintChildren();
    }
    public Boolean currentPlayerMove(String word, int column, int row, boolean isVertical) {
        word = word.toLowerCase();
        if (!tree.contains(word))
            return false;
        ArrayList<Tile> tiles = this.placeWord(word, column, row, isVertical);
        if (tiles == null)
            return false;
        addmove(new Move(getCurrentplayer(), tiles, 1, word));
        return true;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }
}
