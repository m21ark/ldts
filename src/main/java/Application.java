import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

public class Application {

    public static void main(String[] args) {

        try {
            Game game = new Game(new Dimensions(30, 35));
            game.run();
        } catch (IOException | URISyntaxException | FontFormatException e) {
            e.printStackTrace();
        }


    }

}
