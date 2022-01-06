import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
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

    private boolean validKeyChar(Character ch) {
        if (key != null) {
            return key.getKeyType() != KeyType.Character && key.getCharacter() != ch;
        }
        return false;
    }

    private void draw() throws IOException {
        screen.clear();
        arena.getGameScreen().drawGame(graphics, arena.getPlayerScore(), arena.getPlayerHp(),arena.getMatrix());
        screen.refresh();
    }

    public void run() throws IOException {

        boolean runGame = true;

        //Title  Screen
        screen.clear();
        arena.getGameScreen().drawLoadingScreen(graphics);
        screen.refresh();

        do {
            key = screen.readInput();
        } while (validKeyChar('q'));


        //Main Game Screen
        int gameLoopInt = 0;
        do {
            draw();
            key = screen.pollInput();
            runGame = arena.processKey(key, screen);
            if (gameLoopInt % 50 == 0) {
                arena.addRandomElem(1, Arena.blockChar);
                arena.applyGravity();
            }
            if (gameLoopInt == 400) {
                arena.addRandomElem(1, Arena.coinChar);
                gameLoopInt = 0;
            }
            gameLoopInt++;

            arena.update();

        } while (runGame && arena.playerAlive());


        //Ending Screen
        screen.clear();
        arena.getGameScreen().drawDeathScreen(graphics, arena.getPlayerScore());
        screen.refresh();

        do {
            key = screen.readInput();
        } while (validKeyChar('q'));


    }


}

/*
 */