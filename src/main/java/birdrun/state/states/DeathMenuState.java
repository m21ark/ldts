package birdrun.state.states;

import birdrun.controller.GameController;
import birdrun.state.Command;
import birdrun.state.KeyboardObserver;
import com.googlecode.lanterna.screen.Screen;

import java.io.IOException;

public class DeathMenuState {

    private final MenuState menuState;
    private final Screen screen;

    public DeathMenuState(Screen screen, MenuState menuState) {

        this.screen = screen;
        this.menuState = menuState;


    }

    public GameController.STATE start(int score) {

        try {
            screen.clear();
            menuState.drawState(MenuState.MENU_STATE.DEATH, score);
            screen.refresh();
        } catch (IOException e) {
            e.printStackTrace();
        }


        while (true) {

            Command.COMMAND command = new KeyboardObserver(screen).listenRead();

            if (command == null) continue;

            if (command == Command.COMMAND.QUIT) System.exit(0);
            else if (command == Command.COMMAND.SELECT) {
                return GameController.STATE.GAME;
            } else if (command == Command.COMMAND.UP) {
                return GameController.STATE.START;
            }

        }

    }


}
