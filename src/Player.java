import java.util.ArrayList;

public class Player {
    public String name;
    public Integer score;
    public ArrayList<Character> rack;

    public Player(String name) {
        this.name = name;
        this.score = 0;
    }

    @Override
    public String toString() {
        return name + " score: " + score;
    }

    public String getName() {
        return name;
    }

    public Integer getScore() {
        return score;
    }

    public Integer countLetters() {return rack.size()};

    public void draw(Bag bag) {}
}
