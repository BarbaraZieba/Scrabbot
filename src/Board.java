import javafx.util.Pair;

import java.io.IOException;
import java.util.ArrayList;

import static java.lang.System.*;

public class Board {
    public Character[][] board;
    public ArrayList<Tile> tiles;
    public ArrayList<Bonus> bonuses;
    public Bonus.Type[][] bonusboard;
    public Tree tree;


    public Board() throws IOException {
        board = new Character[15][15];
        tiles = new ArrayList<Tile>();
        bonusboard = new Bonus.Type[15][15];
        bonuses = new ArrayList<Bonus>();
        tree = Tree.getInstance();

        for (int i = 0; i < 15; i++)
            for (int j = 0; j < 15; j++)
                bonusboard[i][j] = Bonus.Type.Empty;
        bonusboard[7][7] = Bonus.Type.Center;
        int TWcoordinates[][] = {{0, 0}, {0, 7}, {7, 0}};
        int DWcoordinates[][] = {{1, 1}, {2, 2}, {3, 3}, {4, 4}};
        int TLcoordinates[][] = {{1, 5}, {5, 1}, {5, 5}};
        int DLcoordinates[][] = {{0, 3}, {3, 0}, {2, 6}, {6, 2}, {3, 7}, {7, 3}, {6, 6}};
        for (int i = 0; i < 3; i++) {
            bonusboard[TWcoordinates[i][0]][TWcoordinates[i][1]] = Bonus.Type.TripleWord;
            bonusboard[14 - TWcoordinates[i][0]][TWcoordinates[i][1]] = Bonus.Type.TripleWord;
            bonusboard[TWcoordinates[i][0]][14 - TWcoordinates[i][1]] = Bonus.Type.TripleWord;
            bonusboard[14 - TWcoordinates[i][0]][14 - TWcoordinates[i][1]] = Bonus.Type.TripleWord;
        }
        for (int i = 0; i < 4; i++) {
            bonusboard[DWcoordinates[i][0]][DWcoordinates[i][1]] = Bonus.Type.DoubleWord;
            bonusboard[14 - DWcoordinates[i][0]][DWcoordinates[i][1]] = Bonus.Type.DoubleWord;
            bonusboard[DWcoordinates[i][0]][14 - DWcoordinates[i][1]] = Bonus.Type.DoubleWord;
            bonusboard[14 - DWcoordinates[i][0]][14 - DWcoordinates[i][1]] = Bonus.Type.DoubleWord;
        }
        for (int i = 0; i < 3; i++) {
            bonusboard[TLcoordinates[i][0]][TLcoordinates[i][1]] = Bonus.Type.TripleLetter;
            bonusboard[14 - TLcoordinates[i][0]][TLcoordinates[i][1]] = Bonus.Type.TripleLetter;
            bonusboard[TLcoordinates[i][0]][14 - TLcoordinates[i][1]] = Bonus.Type.TripleLetter;
            bonusboard[14 - TLcoordinates[i][0]][14 - TLcoordinates[i][1]] = Bonus.Type.TripleLetter;
        }
        for (int i = 0; i < 7; i++) {
            bonusboard[DLcoordinates[i][0]][DLcoordinates[i][1]] = Bonus.Type.DoubleLetter;
            bonusboard[14 - DLcoordinates[i][0]][DLcoordinates[i][1]] = Bonus.Type.DoubleLetter;
            bonusboard[DLcoordinates[i][0]][14 - DLcoordinates[i][1]] = Bonus.Type.DoubleLetter;
            bonusboard[14 - DLcoordinates[i][0]][14 - DLcoordinates[i][1]] = Bonus.Type.DoubleLetter;
        }

        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                if (bonusboard[i][j] != Bonus.Type.Empty) {
                    bonuses.add(new Bonus(i, j, bonusboard[i][j]));
                }
            }
        }
    }

    public Pair<ArrayList<Tile>, Integer> placeWord(String word, int column, int row, boolean isVertical) {
        ArrayList<Tile> tiles = new ArrayList<>();
        boolean isTouching = false;
        Integer score = 0;
        if (!tree.contains(word))
            return null;
        if (isVertical) {
            if (row + word.length() > 15) return null;
            if (row-1 >= 0 && board[column][row-1] != null) return null;
            if (row+word.length()+1 < 14 && board[column][row+word.length()+1] != null) return null;
            for (int i = 0; i < word.length(); i++) {
                Character c = board[column][row + i];
                if (c == null) tiles.add(new Tile(word.charAt(i), column, row + i));
                else {
                    isTouching = true;
                    if (c != word.charAt(i))
                        return null;
                }
                if (c == null) {
                    String s = Character.toString(word.charAt(i));
                    int prefix = 0;
                    for (int j = column - 1; j >= 0 && board[j][row + i] != null; j--){
                        s = Character.toString(board[j][row + i]) + s;
                        prefix++;
                    }
                    for (int j = column + 1; j <= 14 && board[j][row + i] != null; j++)
                        s = s + Character.toString(board[j][row + i]);
                    if (s.length() > 1) {
                        isTouching = true;
                        if(!tree.contains(s))
                            return null;
                        score += value(s,column-prefix,row+i,false);
                    }
                }
            }
            if(column == 7 && row<=7 && row+word.length()>=7 ) isTouching = true;
        }
        if (!isVertical) {
            if (column + word.length() > 15) return null;
            if (column-1 >= 0 && board[column-1][row] != null) return null;
            if (column+word.length()+1 < 14 && board[column+word.length()+1][row] != null) return null;
            for (int i = 0; i < word.length(); i++) {
                Character c = board[column + i][row];
                if (c == null) tiles.add(new Tile(word.charAt(i), column + i, row));
                else{
                    isTouching = true;
                    if (c != word.charAt(i))
                        return null;
                }
                if (c == null) {
                    String s = Character.toString(word.charAt(i));
                    int prefix = 0;
                    for (int j = row - 1; j >= 0 && board[column + i][j] != null; j--) {
                        s = Character.toString(board[column + i][j]) + s;
                        prefix--;
                    }
                    for (int j = row + 1; j <= 14 && board[column + i][j] != null; j++)
                        s = s + Character.toString(board[column + i][j]);
                    if (s.length() > 1) {
                        isTouching = true;
                        if(!tree.contains(s))
                            return null;
                        score += value(s,column+i,row-prefix,true);
                    }
                }
            }
            if(row == 7 && column<=7 && column+word.length()>=7 ) isTouching = true;
        }
        if(!isTouching){
            return null;
        }
        score += value(word,column,row,isVertical);
        return new Pair<>(tiles, score);
    }

    public Integer value(String word, int column, int row, boolean isVertical) {
        int wordbonus = 1;
        int letterbonus = 1;
        int value = 0;
        if (isVertical) {
            for (int i = 0; i < word.length(); i++) {
                Character c = board[column][row + i];
                if (c == null) {
                    switch (bonusboard[column][row + i]) {
                        case TripleWord:
                            wordbonus *= 3;
                            break;
                        case DoubleWord:
                        case Center:
                            wordbonus *= 2;
                            break;
                        case TripleLetter:
                            letterbonus = 3;
                            break;
                        case DoubleLetter:
                            letterbonus = 2;
                            break;
                    }
                }
                value += Tile.getValueOf(word.charAt(i))*letterbonus;
                letterbonus = 1;
            }
        }
        else{
            for (int i = 0; i < word.length(); i++) {
                Character c = board[column + i][row];
                if (c == null) {
                    switch (bonusboard[column + i][row]) {
                        case TripleWord:
                            wordbonus *= 3;
                            break;
                        case DoubleWord:
                        case Center:
                            wordbonus *= 2;
                            break;
                        case TripleLetter:
                            letterbonus = 3;
                            break;
                        case DoubleLetter:
                            letterbonus = 2;
                            break;
                    }
                }
                value += Tile.getValueOf(word.charAt(i))*letterbonus;
                letterbonus = 1;
            }
        }
        value *= wordbonus;
        return value;
    }
}


