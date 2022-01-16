package birdrun.state.states

import birdrun.controller.GameController
import birdrun.controller.MenuController
import com.googlecode.lanterna.screen.Screen
import spock.lang.Specification

class Test_InstructionsMenuState extends Specification {
    Screen screen
    MenuController menuController
    InstructionsMenuState instructionsMenuState

    def setup() {
        screen = Mock(Screen.class)
        menuController = Mock(MenuController.class)
        instructionsMenuState = new InstructionsMenuState(screen, menuController) {
            @Override
            public GameController.STATE waitForUserConfirmation(GameController.STATE confirmAction) {return GameController.STATE.NONE}
        }
    }

    def "test start"() {
        when:
        instructionsMenuState.start()

        then:
        1 * menuController.drawState(_)
        1 * screen.clear()
        1 * screen.refresh()
    }

}
