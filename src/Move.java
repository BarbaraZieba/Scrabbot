import java.util.ArrayList;

public class Move {
    public Player player;
    public ArrayList<Tile> tiles;
    public int score;
    public String word;
    public ArrayList<Character> takenTiles;

    public Move(Player player, ArrayList<Tile> tiles, int score, String word, ArrayList<Character> takenTiles) {
        this.player = player;
        this.tiles = tiles;
        this.score = score;
        this.word = word;
        this.takenTiles = takenTiles;
    }

    @Override
    public String toString() {
        return player.getName() + "   " + word + " " + score;
    }
}
