package birdrun.state.states

import birdrun.controller.GameController
import birdrun.controller.MenuController
import com.googlecode.lanterna.screen.Screen
import spock.lang.Specification

class Test_PauseMenuState extends Specification {
    Screen screen
    MenuController menuController
    PauseMenuState pauseState

    def setup() {
        screen = Mock(Screen.class)
        menuController = Mock(MenuController.class)
        pauseState = new PauseMenuState(screen, menuController) {
            @Override
            GameController.STATE waitForUserConfirmation(GameController.STATE confirmAction) {
                return GameController.STATE.NONE
            }
        }
    }

    def "test start"() {
        when:
        pauseState.start()

        then:
        1 * menuController.drawState(_)
        1 * screen.clear()
        1 * screen.refresh()
    }
}
