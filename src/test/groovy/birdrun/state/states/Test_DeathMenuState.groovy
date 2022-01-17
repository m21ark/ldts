package birdrun.state.states

import birdrun.controller.GameController
import birdrun.controller.MenuController
import com.googlecode.lanterna.screen.Screen
import spock.lang.Specification

class Test_DeathMenuState extends Specification {
    Screen screen
    MenuController menuController
    DeathMenuState deathState

    def setup() {
        screen = Mock(Screen.class)
        menuController = Mock(MenuController.class)
        deathState = new DeathMenuState(screen, menuController) {
            @Override
            GameController.STATE waitForUserConfirmation(GameController.STATE confirmAction) {
                return GameController.STATE.NONE
            }
        }
    }

    def "test start"() {
        given:
        int score = 5

        when:
        deathState.start(score)

        then:
        1 * menuController.drawState(_, score)
        1 * screen.clear()
        1 * screen.refresh()
    }
}

