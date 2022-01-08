import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

public class GameViewer{

    public void draw(Screen screen, TextGraphics graphics, ArenaController arena) throws IOException {
        screen.clear();
        arena.getArenaViewer().drawGame(graphics, arena.getPlayerScore(), arena.getPlayerHp(),arena.getMatrix());
        screen.refresh();
    }


}
