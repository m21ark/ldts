package birdrun.state.states;

import birdrun.controller.GameController;
import birdrun.controller.MenuController;
import com.googlecode.lanterna.screen.Screen;

import java.io.IOException;

@SuppressWarnings("CatchAndPrintStackTrace")

public class DeathMenuState extends MenuState {


    public DeathMenuState(Screen screen, MenuController menuController) {
        super(screen, menuController);
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

        return waitForUserConfirmation(GameController.STATE.START);

    }


}
