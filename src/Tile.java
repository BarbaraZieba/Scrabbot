import java.util.Map;
import java.util.TreeMap;

public class Tile {
    public Character letter;
    public int row;
    public int column;

    static Map<Character,Integer> value = new TreeMap<>();
    static {
        value.put('a',1);
        value.put('ą',5);
        value.put('b',3);
        value.put('c',2);
        value.put('ć',6);
        value.put('d',2);
        value.put('e',1);
        value.put('ę',5);
        value.put('f',5);
        value.put('g',3);
        value.put('h',3);
        value.put('i',1);
        value.put('j',3);
        value.put('k',2);
        value.put('l',2);
        value.put('ł',3);
        value.put('m',2);
        value.put('n',1);
        value.put('ń',7);
        value.put('o',1);
        value.put('ó',5);
        value.put('p',2);
        value.put('r',1);
        value.put('s',1);
        value.put('ś',5);
        value.put('t',2);
        value.put('u',3);
        value.put('w',1);
        value.put('y',2);
        value.put('z',1);
        value.put('ź',9);
        value.put('ż',5);
        value.put(' ',5);
    }


    public Tile(Character letter, int row, int column) {
        this.letter = letter;
        this.row = row;
        this.column = column;
    }
}
