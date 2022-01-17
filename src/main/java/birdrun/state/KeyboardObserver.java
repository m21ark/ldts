package birdrun.state;

import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.Screen;

import java.io.IOException;

@SuppressWarnings("CatchAndPrintStackTrace")

public class KeyboardObserver {
    private final Screen screen;

    public KeyboardObserver(Screen screen) {
        this.screen = screen;
    }


    public Command.COMMAND interpertKey(KeyStroke key) {

        if (key == null) return Command.COMMAND.NONE;

        switch (key.getKeyType()) {
            case EOF:
                return Command.COMMAND.QUIT;
            case ArrowUp:
                return Command.COMMAND.UP;
            case ArrowDown:
                return Command.COMMAND.DOWN;
            case ArrowRight:
                return Command.COMMAND.RIGHT;
            case ArrowLeft:
                return Command.COMMAND.LEFT;
            case Enter:
                return Command.COMMAND.SELECT;
            case Character:
                return this.interpertCharKey(key);
            default:
                return Command.COMMAND.NONE;
        }
    }

    public Command.COMMAND interpertCharKey(KeyStroke key) {

        String ch = key.getCharacter().toString().toUpperCase();

        switch (ch) {
            case "Q":
                return Command.COMMAND.QUIT;
            case "P":
                return Command.COMMAND.PAUSE;
            case "W":
                return Command.COMMAND.UP;
            case "A":
                return Command.COMMAND.LEFT;
            case "S":
                return Command.COMMAND.DOWN;
            case "D":
                return Command.COMMAND.RIGHT;
            default:
                return Command.COMMAND.NONE;
        }

    }

    public Command.COMMAND listenRead() {
        try {
            KeyStroke key = this.screen.readInput();
            return interpertKey(key);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;

    }


    public Command.COMMAND listenPoll() {
        try {
            KeyStroke key = this.screen.pollInput();
            return interpertKey(key);
        } catch (IOException e) {
            e.printStackTrace();
        }


        return null;
    }

}
