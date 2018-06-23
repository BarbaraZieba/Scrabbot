import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class Rack {
    protected ArrayList<Character> tiles;

    public Rack() {
        tiles = new ArrayList<>();
    }

    public void add(Character c) {
        tiles.add(c);
    }

    public void take(Character c) {
        for (Character r : tiles)
            if (r.charValue() == c.charValue()) {
                tiles.remove(r);
                return;
            }
    }

    public Boolean contains(ArrayList<Tile> word) {
        word.sort(new Comparator<Tile>() {
            @Override
            public int compare(Tile o1, Tile o2) {
                return o1.letter.compareTo(o2.letter);
            }
        });
        for (int i = 0, j = 0; i < word.size(); i++, j++)
            while (!word.get(i).letter.equals(this.tiles.get(j))) {
                j++;
                if (j == tiles.size())
                    return false;
            }
        return true;
    }

    public Boolean contains(Character c) {
        for (Character r : tiles) {
            if (r.charValue() == c.charValue()) {
                return true;
            }
        }
        return false;
    }

    public Integer size() {
        return tiles.size();
    }

    public ArrayList<Character> getTiles() {
        return tiles;
    }

    public void setTiles(ArrayList<Character> tiles) {
        this.tiles = tiles;
    }


}
