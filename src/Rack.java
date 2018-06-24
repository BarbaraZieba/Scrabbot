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

    public Character take(Character c) {
        for(int i = 0; i<tiles.size(); i++)
            if(c.equals(tiles.get(i)))
                return tiles.remove(i);
        return null;
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

    public ArrayList<Character> takeTiles(ArrayList<Tile> word) {
        ArrayList<Character> taken = new ArrayList<>();
        for (Tile t : word) {
            if (contains(t.letter)) {
                take(t.letter);
                taken.add(t.letter);
            } else {
                for (Character c : taken)
                    add(c);
                return null;
            }
        }
        return taken;
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
