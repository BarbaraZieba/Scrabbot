import java.util.Map;
import java.util.TreeMap;

public class Tile {
    public Character letter;
    public int row;
    public int column;
    private static Map<Character, Integer> value = new TreeMap<>();

    static {
        Character c[] = {'A', 'Ą', 'B', 'C', 'Ć', 'D', 'E', 'Ę', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'Ł',
                'M', 'N', 'Ń', 'O', 'Ó', 'P', 'R', 'S', 'Ś', 'T', 'U', 'W', 'Y', 'Z', 'Ź', 'Ż', ' '};
        int values[] = {1, 5, 3, 2, 6, 2, 1, 5, 5, 3, 3, 1, 3, 2, 2, 3,
                2, 1, 7, 1, 5, 2, 1, 1, 5, 2, 3, 1, 2, 1, 9, 5, 0};
        for (int i = 0; i < c.length; i++)
            value.put(c[i], values[i]);
    }


    public Tile(Character letter, int column, int row) {
        this.letter = letter;
        this.row = row;
        this.column = column;
    }

    public static int getValueOf(Character c) {
        return value.get(c);
    }
}
