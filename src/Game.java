import javafx.util.Pair;

import java.io.IOException;
import java.util.ArrayList;

public class Game extends Board {
    private ArrayList<Player> players;
    private Integer currentplayer;
    public Bag bag;
    public final GameWindow gameWindow;
    public ArrayList<Move> history;

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
        }
        move.player.score += move.score;
        getCurrentplayer().draw(bag);
        currentplayer = (currentplayer + 1) % players.size();
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

        if (last.takenTiles == null)
            return;
        int count = last.takenTiles.size();
        for (int i = 0; i < count; i++)
            bag.addtobag(getCurrentplayer().tiles.remove(getCurrentplayer().tiles.size() - 1), 1);
        for (Character c : last.takenTiles)
            getCurrentplayer().add(c);

        gameWindow.repaintChildren();
    }

    public void currentPlayerPass() {
        addmove(new Move(getCurrentplayer(), null, 0, "-- PASS --", null));
    }

    public Boolean currentPlayerMove(String word, int column, int row, boolean isVertical) {
        Pair<ArrayList<Tile>, Integer> effect = this.placeWord(word, column, row, isVertical);
        if (effect == null)
            return false;
        ArrayList<Character> taken = getCurrentplayer().takeTiles(effect.getKey());
        if (taken == null)
            return false;
        addmove(new Move(getCurrentplayer(), effect.getKey(), effect.getValue(), word, taken));
        return true;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public Bag getBag() {
        return bag;
    }
}
