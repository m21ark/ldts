package birdrun;

import birdrun.controller.GameController;
import birdrun.model.Dimensions;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

@SuppressWarnings("CatchAndPrintStackTrace")


public class Application {

    public static void main(String[] args) {

        try {
            GameController game = new GameController(new Dimensions(30, 28));
            game.run();
        } catch (IOException | URISyntaxException | FontFormatException | InterruptedException e) {
            e.printStackTrace();
        }


    }

}
