import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        new NewGameDialog();

        // pokazówka jak działają enumy
        for (Letter l : Letter.values())
            System.out.println(l);
    }
}
