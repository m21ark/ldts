package birdrun.state.states;

import birdrun.controller.GameController;
import birdrun.controller.MenuController;
import com.googlecode.lanterna.screen.Screen;

import java.io.IOException;

public class PauseMenuState extends MenuState {

    public PauseMenuState(Screen screen, MenuController menuController) {
        super(screen, menuController);
    }

    @Override
    public GameController.STATE start() {

        try {
            screen.clear();
            menuController.drawState(MenuController.MENU_STATE.PAUSE);
            screen.refresh();
        } catch (IOException e) {
            //e.printStackTrace();
        }

        return waitForUserConfirmation(GameController.STATE.GAME);

    }


}

