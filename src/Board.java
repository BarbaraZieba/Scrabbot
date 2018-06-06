import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class Board {
    public Character[][] board;
    public ArrayList<Tile> tiles;
    public ArrayList<Move> history;
    public ArrayList<Bonus> bonuses;
    public ArrayList<Player> players;
    public Tree tree = Tree.getInstance();
    private Integer currentplayer = 0;

    public Board (ArrayList<Player> players) throws IOException {
        board = new Character[15][15];
        history = new ArrayList<Move>();
        tiles = new ArrayList<Tile>();
        bonuses = new ArrayList<Bonus>();
        this.players = players;
        bonuses.add(new Bonus(7,7, Bonus.Type.Center));
        Collections.addAll(bonuses, new Bonus(0,0, Bonus.Type.TripleWord), new Bonus(0,7, Bonus.Type.TripleWord),
                new Bonus(0,14, Bonus.Type.TripleWord), new Bonus(7,0, Bonus.Type.TripleWord),
                new Bonus(7,14, Bonus.Type.TripleWord), new Bonus(14,0, Bonus.Type.TripleWord),
                new Bonus(14,7, Bonus.Type.TripleWord), new Bonus(14,14, Bonus.Type.TripleWord));
        Collections.addAll(bonuses, new Bonus(1,1, Bonus.Type.DoubleWord), new Bonus(2,2, Bonus.Type.DoubleWord),
                new Bonus(3,3, Bonus.Type.DoubleWord), new Bonus(4,4, Bonus.Type.DoubleWord),
                new Bonus(1,13, Bonus.Type.DoubleWord), new Bonus(2,12, Bonus.Type.DoubleWord),
                new Bonus(3,11, Bonus.Type.DoubleWord), new Bonus(4,10, Bonus.Type.DoubleWord),
                new Bonus(10,4, Bonus.Type.DoubleWord), new Bonus(11,3, Bonus.Type.DoubleWord),
                new Bonus(12,2, Bonus.Type.DoubleWord), new Bonus(13,1, Bonus.Type.DoubleWord),
                new Bonus(10,10, Bonus.Type.DoubleWord), new Bonus(11,11, Bonus.Type.DoubleWord),
                new Bonus(12,12, Bonus.Type.DoubleWord), new Bonus(13,13, Bonus.Type.DoubleWord));
        Collections.addAll(bonuses, new Bonus(1,5, Bonus.Type.TripleLetter), new Bonus(5,1, Bonus.Type.TripleLetter),
                new Bonus(5,5, Bonus.Type.TripleLetter), new Bonus(9,9, Bonus.Type.TripleLetter),
                new Bonus(5,9, Bonus.Type.TripleLetter), new Bonus(9,5, Bonus.Type.TripleLetter),
                new Bonus(9,13, Bonus.Type.TripleLetter), new Bonus(13,9, Bonus.Type.TripleLetter),
                new Bonus(1,9, Bonus.Type.TripleLetter), new Bonus(9,1, Bonus.Type.TripleLetter),
                new Bonus(5,13, Bonus.Type.TripleLetter), new Bonus(13,5, Bonus.Type.TripleLetter));
        Collections.addAll(bonuses, new Bonus(6,6, Bonus.Type.DoubleLetter), new Bonus(8,8, Bonus.Type.DoubleLetter),
                new Bonus(6,8, Bonus.Type.DoubleLetter), new Bonus(8,6, Bonus.Type.DoubleLetter),
                new Bonus(0,3, Bonus.Type.DoubleLetter), new Bonus(3,0, Bonus.Type.DoubleLetter),
                new Bonus(11,14, Bonus.Type.DoubleLetter), new Bonus(14,11, Bonus.Type.DoubleLetter),
                new Bonus(0,11, Bonus.Type.DoubleLetter), new Bonus(11,0, Bonus.Type.DoubleLetter),
                new Bonus(3,14, Bonus.Type.DoubleLetter), new Bonus(14,3, Bonus.Type.DoubleLetter),
                new Bonus(2,6, Bonus.Type.DoubleLetter), new Bonus(6,2, Bonus.Type.DoubleLetter),
                new Bonus(3,7, Bonus.Type.DoubleLetter), new Bonus(7,3, Bonus.Type.DoubleLetter),
                new Bonus(2,8, Bonus.Type.DoubleLetter), new Bonus(8,2, Bonus.Type.DoubleLetter),
                new Bonus(6,12, Bonus.Type.DoubleLetter), new Bonus(12,6, Bonus.Type.DoubleLetter),
                new Bonus(7,11, Bonus.Type.DoubleLetter), new Bonus(11,7, Bonus.Type.DoubleLetter),
                new Bonus(8,12, Bonus.Type.DoubleLetter), new Bonus(12,8, Bonus.Type.DoubleLetter));
    }

    public Player getCurrentplayer() {
        return players.get(currentplayer);
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
    /** głupia funkcja ale potrzebna
     należy ją przerobić tak żeby była łatwa w obsłudze
     ale wywoływała addmove od obecnego gracza, tworzyła tilevector itd
     */
     public Boolean placeWord(String word, int column, int row, boolean isVertical){
         
         
         
         if (tree.contains(word)){
             for(int i = 0; i<word.length(); i++)
            {

                board[column][row] = word.charAt(i);
                if(isVertical) row++;
                else column++;
            }
            return true;
         }
         return false;
    }
}
