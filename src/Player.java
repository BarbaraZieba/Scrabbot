public class Player extends Rack {
    private String name;
    private Integer score;

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

    public void addToScore(int score){
        this.score = Math.min(0,this.score-score);
    }

    public void draw(Bag bag) {
        while (bag.remainingTiles() > 0 && size() < 7)
            add(bag.getRandomTile());
    }
}
