public enum Letter {
    A('a', 9, 1),
    Ą('ą', 1, 5),
    B('b', 6, 2);

    int value;
    int count;
    Character character;

    Letter(Character letter, int value, int count) {
        this.value = value;
        this.count = count;
        this.character = letter;
    }

    @Override
    public String toString() {
        return "Letter{" +
                "value=" + value +
                ", count=" + count +
                ", letter=" + character +
                '}';
    }
}
