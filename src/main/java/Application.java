import java.io.IOException;

public class Application {

    public static void main(String[] args) {

        try {
            Game game = new Game(70,30);
            game.run();
            System.exit(0);

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}