public class Player {
    public String name;
    public int score;

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

    public int getScore() {
        return score;
    }
}
