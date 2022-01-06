import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;

public class Game {

    private final Screen screen;
    private final Bird bird = new Bird(new Position(3, 5), 'B', "#AA0000");

    public Game(int width, int height) throws IOException {

        TerminalSize terminalSize = new TerminalSize(width, height);
        DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory().setInitialTerminalSize(terminalSize);

        Terminal terminal = terminalFactory.createTerminal();
        screen = new TerminalScreen(terminal);


        screen.setCursorPosition(null); // we don't need a cursor
        screen.startScreen(); // screens must be started
        screen.doResizeIfNecessary(); // resize screen if necessary
    }

    public void moveBird(Position pos) {
        bird.setPos(pos);
    }

    public boolean processKey(KeyStroke key, Screen screen) throws IOException {
        if (key.getKeyType() == KeyType.ArrowLeft) {
            moveBird(bird.moveLeft(1));
        } else if (key.getKeyType() == KeyType.ArrowRight) {
            moveBird(bird.moveRight(1));
        } else if (key.getKeyType() == KeyType.ArrowUp) {
            moveBird(bird.moveUp(1));
        } else if (key.getKeyType() == KeyType.ArrowDown) {
            moveBird(bird.moveDown(1));
        } else if (key.getKeyType() == KeyType.EOF) {
            return false;
        }

        if (key.getKeyType() == KeyType.Character && key.getCharacter() == 'q') {
            System.out.println("End of Game!");
            System.exit(0);
            screen.close();
        }

        return true;

    }

    private void draw() throws IOException {
        screen.clear();
        bird.draw(screen.newTextGraphics());
        screen.refresh();
    }


    public void run() throws IOException {

        boolean runGame = true;

        do {
            draw();
            KeyStroke key = screen.readInput();
            runGame = processKey(key, screen);

        } while (runGame);


    }


}

/*
 */