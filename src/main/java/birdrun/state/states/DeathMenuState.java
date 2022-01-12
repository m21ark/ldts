package birdrun.state.states;

import birdrun.controller.GameController;
import birdrun.controller.MenuController;
import birdrun.state.Command;
import birdrun.state.KeyboardObserver;
import com.googlecode.lanterna.screen.Screen;

import java.io.IOException;

public class DeathMenuState extends MenuState {

    private final MenuController menuController;
    private final Screen screen;

    public DeathMenuState(Screen screen, MenuController menuController) {
        super(screen, menuController);

        this.screen = screen;
        this.menuController = menuController;

    }

    @Override
    public GameController.STATE start() {
        return null;
    }

    public GameController.STATE start(int score) {

        try {
            screen.clear();
            menuController.drawState(MenuController.MENU_STATE.DEATH, score);
            screen.refresh();

        } catch (IOException e) {
            e.printStackTrace();
        }


        while (true) {
            Command.COMMAND command = new KeyboardObserver(screen).listenRead();

            if (command == null) continue;
            if (command == Command.COMMAND.QUIT) System.exit(0);
            else if (command == Command.COMMAND.SELECT) {
                return GameController.STATE.START;
            }
        }

    }


}
