import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

public class Tree {
    private static Node root;
    private static Tree instance = null;
    private Tree() throws IOException {
        root = new Node(false,null);
        wordsfromfile("slowa.txt");
    }

    public class Node{
            private boolean data;
            private Node parent;
            private Map<Character,Node> children;
            public Node(boolean data, Node parent){
                this.data = data;
                this.parent = parent;
                this.children = new TreeMap<>();
            }
    }

    public static Tree getInstance() throws IOException{
        if (instance == null) {
            instance = new Tree();
        }
        return instance;
    }

    public void add(String textLine) {
        int i = 0;
        Node temp = root;
        while (i<textLine.length()){
            if(!temp.children.containsKey(textLine.charAt(i)))
            {
                temp.children.put(textLine.charAt(i),new Node(false, temp));
            }
            temp = temp.children.get(textLine.charAt(i));
            i++;
        }
        temp.data = true;
    }

    private void wordsfromfile(String filePath) throws IOException {
        FileReader fileReader = new FileReader(filePath);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String textLine = bufferedReader.readLine();
        while(textLine != null){
            this.add(textLine);
            textLine = bufferedReader.readLine();
        }
        bufferedReader.close();
    }

    public Boolean contains(String word){
        word = word.toLowerCase();
        int i = 0;
        Node temp = root;
        while (i<word.length()){
            if(!temp.children.containsKey(word.charAt(i)))
            {
                return false;
            }
            temp = temp.children.get(word.charAt(i));
            i++;
        }
        return temp.data;
    }
}
