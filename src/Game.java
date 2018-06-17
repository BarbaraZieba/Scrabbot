import java.io.IOException;
import java.util.ArrayList;

public class Game {
    public Board board;
    private ArrayList<Player> players;
    private Integer currentplayer;
    public Bag bag;
    public final GameWindow gameWindow;
    public Tree tree = Tree.getInstance();
    public ArrayList<Move> history;

    public Game(GameWindow gameWindow, ArrayList<Player> players) throws IOException {
        this.board = new Board();
        this.currentplayer = 0;
        this.players = players;
        this.bag = new Bag();
        this.bag.loadPolishScrabble();
        this.gameWindow = gameWindow;
        this.history = new ArrayList<>();
    }

    public Board getBoard() {
        return board;
    }

    public Player getCurrentplayer() {
        return players.get(currentplayer);
    }

    public void addmove(Move move) {
        System.out.println(move);
        history.add(move);
        gameWindow.getHistoryPanel().getListModel().addElement(move);
        board.tiles.addAll(move.tiles);
        for (Tile t : move.tiles) {
            board.board[t.column][t.row] = t.letter;
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
            board.board[t.column][t.row] = null;
            board.tiles.remove(t);
        }
        last.player.score -= last.score;
        currentplayer = (currentplayer -1 + players.size()) % players.size();
        gameWindow.repaintChildren();
    }
    public Boolean currentPlayerMove(String word, int column, int row, boolean isVertical) {
        word = word.toLowerCase();
        if (!tree.contains(word))
            return false;
        ArrayList<Tile> tiles = board.placeWord(word, column, row, isVertical);
        if (tiles == null)
            return false;
        addmove(new Move(getCurrentplayer(), tiles, 1, word));
        return true;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }
}
