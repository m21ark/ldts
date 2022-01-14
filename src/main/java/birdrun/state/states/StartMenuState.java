package birdrun.state.states;

import birdrun.controller.GameController;
import birdrun.controller.MenuController;
import birdrun.state.Command;
import birdrun.state.KeyboardObserver;
import com.googlecode.lanterna.screen.Screen;

import java.io.IOException;

public class StartMenuState extends MenuState {



    public StartMenuState(Screen screen, MenuController menuController) {
        super(screen, menuController);
    }

    @Override
    public GameController.STATE start() {

        try {
            screen.clear();
            menuController.drawState(MenuController.MENU_STATE.INITIAL);
            screen.refresh();
        } catch (IOException e) {
           // e.printStackTrace();
        }


        while (true) {

            Command.COMMAND command = new KeyboardObserver(screen).listenRead();

            if (command == null) continue;
            if (command == Command.COMMAND.QUIT) System.exit(0);
            else if (command == Command.COMMAND.SELECT) {
                return GameController.STATE.GAME;
            } else if (command == Command.COMMAND.UP) {
                return GameController.STATE.INSTRUCTIONS;
            }

        }

    }


}
