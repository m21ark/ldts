package birdrun.state.states

import birdrun.controller.MenuController
import birdrun.state.Command
import birdrun.state.KeyboardObserver
import com.googlecode.lanterna.screen.Screen
import spock.lang.Specification


class Test_StartMenuState extends Specification  {
    KeyboardObserver keyboardObserver
    Screen screen
    MenuController menuController
    StartMenuState startState

    def setup() {
        keyboardObserver = Stub(KeyboardObserver.class)
        keyboardObserver.listenRead() >> Command.COMMAND.QUIT
        screen = Mock(Screen.class)
        menuController = Mock(MenuController.class)
        startState = new StartMenuState(screen, menuController)
    }

    def "test start"() {
        when:
        startState.start(keyboardObserver)

        then:
        1 * menuController.drawState(_)
        1 * screen.clear()
        1 * screen.refresh()
    }


}
