package birdrun.controller

import birdrun.model.Dimensions
import com.googlecode.lanterna.graphics.TextGraphics
import spock.lang.Specification

class Test_MenuController extends Specification {

    int width, height
    MenuController menuController
    TextGraphics graphics

    def setup() {

        width = 20
        height = 30

        Dimensions dimensions = new Dimensions(width, height)
        graphics = Mock(TextGraphics)

        menuController = new MenuController(dimensions, graphics, "#00ff00", "#00ff00")
    }


    def "Test  DeathState"() {

        when:

        boolean n1 = menuController.drawState(MenuController.MENU_STATE.DEATH, 5)
        boolean n2 = menuController.drawState(MenuController.MENU_STATE.INITIAL, 5)

        then:

        n1
        !n2


    }

    def "Test  Switch"() {

        when:

        int initial = menuController.drawState(MenuController.MENU_STATE.INITIAL,)
        int instructions = menuController.drawState(MenuController.MENU_STATE.INSTRUCTIONS)
        int pause = menuController.drawState(MenuController.MENU_STATE.PAUSE)
        int none = menuController.drawState(MenuController.MENU_STATE.NONE)

        then:

        initial == 0
        instructions == 1
        pause == 2
        none == -1

        3 * graphics.setBackgroundColor(_)

        6 * graphics.setForegroundColor(_)

        3 * graphics.enableModifiers(_)


    }


}


