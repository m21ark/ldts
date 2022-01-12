package birdrun.state.states;

import birdrun.controller.GameController;
import birdrun.controller.MenuController;
import com.googlecode.lanterna.screen.Screen;

public abstract class MenuState {


    private final MenuController menuController;
    private final Screen screen;

    public MenuState(Screen screen, MenuController menuController) {

        this.screen = screen;
        this.menuController = menuController;

    }

    public abstract GameController.STATE start();


}
