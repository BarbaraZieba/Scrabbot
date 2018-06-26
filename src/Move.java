import java.util.ArrayList;

public class Move {
    public final Player player;
    public final ArrayList<Tile> tiles;
    public final int score;
    public final String word;
    public final ArrayList<Character> takenTiles;

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

    public boolean isPass() {
        return word.equals("-- PASS --") || (tiles == null && takenTiles == null);
    }
}
