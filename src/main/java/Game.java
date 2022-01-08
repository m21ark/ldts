import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.terminal.swing.AWTTerminalFontConfiguration;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

public class Game {

    private final Screen screen;
    private final TextGraphics graphics;
    private final ArenaController arena;
    private KeyStroke key;
    private MenuViewer menuViewer;


    public Game(int width, int height) throws IOException, URISyntaxException, FontFormatException {

        this.screen = new ScreenFactory().getScreen(width, height, 20);

        this.graphics = screen.newTextGraphics();

        this.arena = new ArenaController(width, height);

        menuViewer = new  MenuViewer(width,height, "#3A656C","#000000");

    }

    private boolean validKeyChar(Character ch) {
        if (key != null) {
            return key.getKeyType() != KeyType.Character && key.getCharacter() != ch;
        }
        return false;
    }

    private void draw() throws IOException {
        screen.clear();
        arena.getArenaViewer().drawGame(graphics, arena.getPlayerScore(), arena.getPlayerHp(),arena.getMatrix());
        screen.refresh();
    }

    public void run() throws IOException {

        boolean runGame = true;

        //Title  Screen
        screen.clear();
        menuViewer.drawLoadingScreen(graphics);
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
                arena.addRandomBlock(1);
                arena.applyGravity();
            }
            if (gameLoopInt == 400) {
                arena.addRandomCoin(1);
                gameLoopInt = 0;
            }
            gameLoopInt++;

            arena.update();

        } while (runGame && arena.playerAlive());


        //Ending Screen
        screen.clear();
        menuViewer.drawDeathScreen(graphics, arena.getPlayerScore());
        screen.refresh();

        do {
            key = screen.readInput();
        } while (validKeyChar('q'));


    }


}

/*
 */