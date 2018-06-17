import java.util.LinkedList;
import java.util.Random;

public class Bag {
    private LinkedList<Character> tiles;
    private Random random;

    public Bag() {
        this.tiles = new LinkedList<Character>();
        this.random = new Random();
    }

    public void loadPolishScrabble() {
        Character c[] = {'a', 'ą', 'b', 'c', 'ć', 'd', 'e', 'ę', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'ł',
                'm', 'n', 'ń', 'o', 'ó', 'p', 'r', 's', 'ś', 't', 'u', 'w', 'y', 'z', 'ź', 'ż', ' '};
        int times[] = {9, 1, 2, 3, 1, 3, 7, 1, 1, 2, 2, 8, 2, 3, 3, 2,
                3, 5, 1, 6, 1, 3, 4, 4, 1, 3, 2, 4, 4, 5, 1, 1, 2};
        for (int i = 0; i < c.length; i++)
            addtobag(c[i], times[i]);
    }

    public void addtobag(Character c, int times) {
        for (int i = 0; i < times; i++) {
            tiles.add(c);
        }
    }

    public Character getRandomTile() {
        int r = random.nextInt(remainingTiles());
        Character tile = tiles.remove(r);
        return tile;
    }

    public int remainingTiles() {
        return tiles.size();
    }
}
