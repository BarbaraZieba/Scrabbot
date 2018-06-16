import java.io.IOException;
import java.util.ArrayList;

public class Game {
    public Board board;
    public ArrayList<Player> players;
    private Integer currentplayer;
    public Bag bag;

    public Game(ArrayList<Player> players) throws IOException {
        this.board = new Board();
        this.currentplayer = 0;
        this.players = players;
        this.bag = new Bag();
        this.bag.loadPolishScrabble();
    }

    public Player getCurrentplayer() {
        return players.get(currentplayer);
    }
}
