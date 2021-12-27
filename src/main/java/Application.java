import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;

public class Application {
    public static void main(String[] args) {





        try {
            Terminal terminal = new  DefaultTerminalFactory().createTerminal();
            Screen screen = new TerminalScreen(terminal);
            screen.setCursorPosition(null);
            screen.startScreen();
            screen.doResizeIfNecessary();
            TerminalSize terminalSize = new TerminalSize(40, 20);
            DefaultTerminalFactory terminalFactory = new  DefaultTerminalFactory().setInitialTerminalSize(terminalSize);



            //create a single char for testing
            screen.clear();
            screen.setCharacter(10, 10, TextCharacter.fromCharacter('H')[0]);
            screen.refresh();



        } catch (IOException e) {
            e.printStackTrace();
        }





    }
}