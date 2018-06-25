import javafx.util.Pair;

import java.io.IOException;
import java.util.ArrayList;

public class Game extends Board {
    private ArrayList<Player> players;
    private Integer currentplayer;
    public Bag bag;
    public final GameWindow gameWindow;
    public ArrayList<Move> history;
    public static final int SUCCESS = 0;
    public static final int INVALID_WORD = 1;
    public static final int MISPLACED_WORD = 2;
    public static final int WRONG_TILES = 3;

    public Game(GameWindow gameWindow, ArrayList<Player> players) throws IOException {
        super();
        this.currentplayer = 0;
        this.players = players;
        this.bag = new Bag();
        this.bag.loadPolishScrabble();
        this.gameWindow = gameWindow;
        this.history = new ArrayList<>();

        for (Player p : players)
            p.draw(bag);
    }

    public Player getCurrentplayer() {
        return players.get(currentplayer);
    }

    public void addmove(Move move) {
        System.out.println(move);
        history.add(move);
        gameWindow.getHistoryPanel().getListModel().addElement(move);
        if (move.tiles != null) {
            this.tiles.addAll(move.tiles);
            for (Tile t : move.tiles) {
                this.board[t.column][t.row] = t.letter;
            }
        } else if (move.takenTiles != null) {
            for (Character c : move.takenTiles) {
                bag.addtobag(c, 1);
            }
        }
        NewLettersDialog.call(gameWindow, getCurrentplayer());
        move.player.score += move.score;
        getCurrentplayer().draw(bag);
        currentplayer = (currentplayer + 1) % players.size();
        gameWindow.getRackPanel().getTilePanel().setShouldPaintTiles(false);
        gameWindow.repaintChildren();
    }

    public void deletelastmove() {
        if (history.size() <= 0)
            return;
        Move last = history.get(history.size() - 1);
        gameWindow.getHistoryPanel().getListModel().removeElement(last);
        history.remove(history.size() - 1);
        if (last.tiles != null) {
            for (Tile t : last.tiles) {
                this.board[t.column][t.row] = null;
                this.tiles.remove(t);
            }
        }
        last.player.score -= last.score;
        currentplayer = (currentplayer - 1 + players.size()) % players.size();

        if (last.takenTiles != null) {
            int count = last.takenTiles.size();
            for (int i = 0; i < count; i++)
                bag.addtobag(getCurrentplayer().tiles.remove(getCurrentplayer().tiles.size() - 1), 1);
            for (Character c : last.takenTiles)
                getCurrentplayer().add(c);
        }
        gameWindow.getRackPanel().getTilePanel().setShouldPaintTiles(false);
        gameWindow.repaintChildren();
        NextPlayerDialog.call(gameWindow);
    }

    public void currentPlayerPass() {
        addmove(new Move(getCurrentplayer(), null, 0, "-- PASS --", null));
    }

    public int currentPlayerMove(String word, int column, int row, boolean isVertical) {

        if (!dictionary.contains(word))
            return INVALID_WORD;
        Pair<ArrayList<Tile>, Integer> effect = this.placeWord(word, column, row, isVertical);
        if (effect == null)
            return MISPLACED_WORD;
        ArrayList<Character> taken = getCurrentplayer().takeTiles(effect.getKey());
        if (taken == null)
            return WRONG_TILES;
        addmove(new Move(getCurrentplayer(), effect.getKey(), effect.getValue(), word, taken));
        return SUCCESS;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public Bag getBag() {
        return bag;
    }
}
