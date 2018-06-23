import java.util.ArrayList;

public class Rack {
    protected ArrayList<Character> tiles;

    public Rack() {
        tiles = new ArrayList<>();
    }

    public void add(Character c){ tiles.add(c); }

    public void take(Character c){
        for (Character r: tiles)
            if (r.charValue() == c.charValue()) {
                tiles.remove(r);
                return;
            }
    }

    public Boolean contains(Character c){
        for (Character r: tiles) {
            if (r.charValue() == c.charValue()) {
                return true;
            }
        }
        return false;
    }

    public Integer size(){ return tiles.size(); }

    public ArrayList<Character> getTiles() {
        return tiles;
    }

    public void setTiles(ArrayList<Character> tiles) {
        this.tiles = tiles;
    }


}
