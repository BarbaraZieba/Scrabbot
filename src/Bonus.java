public class Bonus {
    public final Type type;
    public final int row;
    public final int column;

    public enum Type {
        Empty,
        DoubleWord,
        TripleWord,
        DoubleLetter,
        TripleLetter,
        Center
    }

    public Bonus(int row, int column, Type type) {
        this.row = row;
        this.column = column;
        this.type = type;
    }
}