package birdrun.controller;

import birdrun.model.Dimensions;
import com.googlecode.lanterna.TerminalSize;
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

public class ScreenFactory {

    public Font loadFont(int fontSize) throws IOException, FontFormatException, URISyntaxException {

        URL resource = getClass().getClassLoader().getResource("fonts/square.ttf");
        File fontFile = new File(resource.toURI());
        Font font = Font.createFont(Font.TRUETYPE_FONT, fontFile);

        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        ge.registerFont(font);

        return font.deriveFont(Font.BOLD, fontSize);
    }


    public Screen createScreen(Terminal terminal) throws IOException {
        Screen screen = new TerminalScreen(terminal);
        screen.setCursorPosition(null);
        screen.startScreen();
        screen.doResizeIfNecessary();
        return screen;
    }

    public Screen getScreen(Dimensions dimensions, int fontSize) throws IOException, URISyntaxException, FontFormatException {

        int width = dimensions.getWidth();
        int height = dimensions.getHeight();

        TerminalSize terminalSize = new TerminalSize(width, height);
        DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory().setInitialTerminalSize(terminalSize);

        Font loadedFont = loadFont(fontSize);

        AWTTerminalFontConfiguration fontConfig = AWTTerminalFontConfiguration.newInstance(loadedFont);
        terminalFactory.setTerminalEmulatorFontConfiguration(fontConfig);
        terminalFactory.setForceAWTOverSwing(true);

        Terminal terminal = terminalFactory.createTerminal();

        return createScreen(terminal);

    }


}
