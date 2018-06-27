import javax.swing.*;
import java.io.*;
import java.util.Map;
import java.util.TreeMap;

public class Tree {
    private Node root;

    private Tree(String filepath) throws IOException {
        root = new Node(false, null);
        wordsfromfile(filepath);
    }

    public class Node {
        private boolean data;
        private Node parent;
        private Map<Character, Node> children;

        public Node(boolean data, Node parent) {
            this.data = data;
            this.parent = parent;
            this.children = new TreeMap<>();
        }
    }

    public static Tree loadPolishScrabble() {
        try {
            return new Tree("slowa.txt");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Could not load polish dictionary.\n"
                    + "Make sure that 'slowa.txt' is in the same folder as the executable file.\n" + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    public void add(String textLine) {
        int i = 0;
        Node temp = root;
        while (i < textLine.length()) {
            if (!temp.children.containsKey(textLine.charAt(i))) {
                temp.children.put(textLine.charAt(i), new Node(false, temp));
            }
            temp = temp.children.get(textLine.charAt(i));
            i++;
        }
        temp.data = true;
    }

    private void wordsfromfile(String filePath) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(filePath), "UTF8"));
        String textLine = bufferedReader.readLine();
        while (textLine != null) {
            this.add(textLine);
            textLine = bufferedReader.readLine();
        }
        bufferedReader.close();
    }

    public Boolean contains(String word) {
        word = word.toLowerCase();
        int i = 0;
        Node temp = root;
        while (i < word.length()) {
            if (!temp.children.containsKey(word.charAt(i))) {
                return false;
            }
            temp = temp.children.get(word.charAt(i));
            i++;
        }
        return temp.data;
    }
}
