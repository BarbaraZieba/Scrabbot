import java.io.IOException;
import java.util.ArrayList;

public class Game {
    public Board board;
    public ArrayList<Player> players;
    private Integer currentplayer;
    private ArrayList<Character> bag;

    public Game(ArrayList<Player> players) throws IOException {
        this.board = new Board();
        this.currentplayer = 0;
        this.players = players;
        addtobag('a',9);
        addtobag('ą',1);
        addtobag('b',2);
        addtobag('c',3);
        addtobag('ć',1);
        addtobag('d',3);
        addtobag('e',7);
        addtobag('ę',1);
        addtobag('f',1);
        addtobag('g',2);
        addtobag('h',2);
        addtobag('i',8);
        addtobag('j',2);
        addtobag('k',3);
        addtobag('l',3);
        addtobag('ł',2);
        addtobag('m',3);
        addtobag('n',5);
        addtobag('ń',1);
        addtobag('o',6);
        addtobag('ó',1);
        addtobag('p',3);
        addtobag('r',4);
        addtobag('s',4);
        addtobag('ś',1);
        addtobag('t',3);
        addtobag('u',2);
        addtobag('w',4);
        addtobag('y',4);
        addtobag('z',5);
        addtobag('ź',1);
        addtobag('ż',1);
        addtobag(' ',2);
    }

    public Player getCurrentplayer() {
        return players.get(currentplayer);
    }

    public void addtobag(Character c, int times) {
        for(int i=0; i<times; i++)
        {
            bag.add(c);
        }
    }
}
