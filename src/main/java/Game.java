import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;

public class Game {

    private final Screen screen;
    private final TextGraphics graphics;
    private final Arena arena;
    private KeyStroke key;


    public Game(int width, int height) throws IOException {

        TerminalSize terminalSize = new TerminalSize(width, height);
        DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory().setInitialTerminalSize(terminalSize);

        Terminal terminal = terminalFactory.createTerminal();
        screen = new TerminalScreen(terminal);
        screen.setCursorPosition(null); // we don't need a cursor
        screen.startScreen(); // screens must be started
        screen.doResizeIfNecessary(); // resize screen if necessary

        this.graphics = screen.newTextGraphics();

        this.arena = new Arena(width, height);

    }

    private void draw() throws IOException {
        screen.clear();
        arena.draw(graphics);
        screen.refresh();
    }

    public void run() throws IOException {

        boolean runGame = true;

        //Main Game Screen
        do {
            draw();
            key = screen.readInput();
            runGame = arena.processKey(key, screen);

            arena.addRandomElem(1, Arena.blockChar);
            arena.addRandomElem(1, Arena.coinChar);
            arena.applyGravity();

            arena.update();

        } while (runGame && arena.playerAlive());

    }

}
