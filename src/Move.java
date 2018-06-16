import java.util.ArrayList;

public class Move {
    public Player player;
    public ArrayList<Tile> tiles;
    public int score;
    public String word;

    public Move(Player player, ArrayList<Tile> tiles, int score, String word) {
        this.player = player;
        this.tiles = tiles;
        this.score = score;
        this.word = word;
    }

    @Override
    public String toString() {
        return player.getName() + "   " + word + " " + score;
    }
}
