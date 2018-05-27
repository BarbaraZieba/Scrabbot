import java.util.ArrayList;

public class Board {
    public Character[][] board;
    public ArrayList<Tile> tiles;
    public ArrayList<Move> history;
    public ArrayList<Bonus> bonuses;

    public Board (){
        board = new Character[15][15];
        history = new ArrayList<Move>();
        tiles = new ArrayList<Tile>();
    }

    public void addmove(Move move){
        this.history.add(move);
        this.tiles.addAll(move.tiles);
        for (Tile t: move.tiles) {
            board[t.row][t.column] = t.letter;
        }
    }

}
