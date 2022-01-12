package birdrun.viewer;

import birdrun.model.ArenaModel;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;

import java.io.IOException;

public class GameViewer {

    public void draw(Screen screen, TextGraphics graphics, ArenaModel arenaModel, ArenaViewer arenaViewer) throws IOException {
        screen.clear();
        arenaViewer.draw(graphics, arenaModel.getPlayerScore(), arenaModel.getPlayerHp(), arenaModel.getMatrix());
        screen.refresh();
    }

}
