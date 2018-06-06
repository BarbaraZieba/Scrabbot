import java.util.Map;
import java.util.TreeMap;

public class Tile {
    public Character letter;
    public int row;
    public int column;

    static Map<Character,Integer> value = new TreeMap<>();
    static {
        value.put('A',1);
        value.put('Ą',5);
        value.put('B',3);
        value.put('C',2);
        value.put('Ć',6);
        value.put('D',2);
        value.put('E',1);
        value.put('Ę',5);
        value.put('F',5);
        value.put('G',3);
        value.put('H',3);
        value.put('I',1);
        value.put('J',3);
        value.put('K',2);
        value.put('L',2);
        value.put('Ł',3);
        value.put('M',2);
        value.put('N',1);
        value.put('Ń',7);
        value.put('O',1);
        value.put('Ó',5);
        value.put('P',2);
        value.put('R',1);
        value.put('S',1);
        value.put('Ś',5);
        value.put('T',2);
        value.put('U',3);
        value.put('W',1);
        value.put('Y',2);
        value.put('Z',1);
        value.put('Ź',9);
        value.put('Ż',5);



    }


    public Tile(Character letter, int row, int column) {
        this.letter = letter;
        this.row = row;
        this.column = column;
    }
}
