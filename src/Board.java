import java.util.ArrayList;
import java.util.Collections;

public class Board {
    public Character[][] board;
    public ArrayList<Tile> tiles;
    public ArrayList<Move> history;
    public ArrayList<Bonus> bonuses;
    public ArrayList<Player> players;

    public Board (ArrayList<Player> players){
        board = new Character[15][15];
        history = new ArrayList<Move>();
        tiles = new ArrayList<Tile>();
        bonuses = new ArrayList<Bonus>();
        this.players = players;
        bonuses.add(new StartBonus(7,7));
        Collections.addAll(bonuses, new TWBonus(0,0), new TWBonus(0,7),
                new TWBonus(0,14), new TWBonus(7,0),
                new TWBonus(7,14), new TWBonus(14,0),
                new TWBonus(14,7), new TWBonus(14,14));
        Collections.addAll(bonuses, new DWBonus(1,1), new DWBonus(2,2),
                new DWBonus(3,3), new DWBonus(4,4),
                new DWBonus(1,13), new DWBonus(2,12),
                new DWBonus(3,11), new DWBonus(4,10),
                new DWBonus(10,4), new DWBonus(11,3),
                new DWBonus(12,2), new DWBonus(13,1),
                new DWBonus(10,10), new DWBonus(11,11),
                new DWBonus(12,12), new DWBonus(13,13));
        Collections.addAll(bonuses, new TLBonus(1,5), new TLBonus(5,1),
                new TLBonus(5,5), new TLBonus(9,9),
                new TLBonus(5,9), new TLBonus(9,5),
                new TLBonus(9,13), new TLBonus(13,9),
                new TLBonus(1,9), new TLBonus(9,1),
                new TLBonus(5,13), new TLBonus(13,5));
        Collections.addAll(bonuses, new DLBonus(6,6), new DLBonus(8,8),
                new DLBonus(6,8), new DLBonus(8,6),
                new DLBonus(0,3), new DLBonus(3,0),
                new DLBonus(11,14), new DLBonus(14,11),
                new DLBonus(0,11), new DLBonus(11,0),
                new DLBonus(3,14), new DLBonus(14,3),
                new DLBonus(2,6), new DLBonus(6,2),
                new DLBonus(3,7), new DLBonus(7,3),
                new DLBonus(2,8), new DLBonus(8,2),
                new DLBonus(6,12), new DLBonus(12,6),
                new DLBonus(7,11), new DLBonus(11,7),
                new DLBonus(8,12), new DLBonus(12,8));
    }

    public void addmove(Move move){
        this.history.add(move);
        this.tiles.addAll(move.tiles);
        for (Tile t: move.tiles) {
            board[t.row][t.column] = t.letter;
        }
        move.player.score += move.score;
    }

    public void deletelastmove (){
        Move last = history.get(history.size()-1);
        history.remove(history.size()-1);
        for (Tile t: last.tiles){
            board[t.row][t.column] = null;
            tiles.remove(t);
        }
        last.player.score -= last.score;
    }

}
