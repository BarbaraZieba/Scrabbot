import java.util.ArrayList;

public class Player extends Rack{
    public String name;
    public Integer score;

    public Player(String name) {
        super();
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

    public void draw(Bag bag) {  }
}
