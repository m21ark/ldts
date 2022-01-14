package birdrun.state.states;

import birdrun.controller.GameController;
import birdrun.controller.MenuController;
import birdrun.state.Command;
import birdrun.state.KeyboardObserver;
import com.googlecode.lanterna.screen.Screen;

public abstract class MenuState {


    protected MenuController menuController;
    protected Screen screen;

    public MenuState(Screen screen, MenuController menuController) {

        this.screen = screen;
        this.menuController = menuController;

    }

    public abstract GameController.STATE start();


    public GameController.STATE waitForUserConfirmation(GameController.STATE confirmAction){
        while (true) {
            Command.COMMAND command = new KeyboardObserver(screen).listenRead();

            if (command == null) continue;
            if (command == Command.COMMAND.QUIT) System.exit(0);
            else if (command == Command.COMMAND.SELECT) {
                return confirmAction;
            }
        }
    }


}
