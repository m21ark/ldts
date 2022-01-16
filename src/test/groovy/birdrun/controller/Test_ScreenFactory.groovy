package birdrun.controller

import birdrun.controller.ScreenFactory
import birdrun.model.Dimensions
import com.googlecode.lanterna.screen.Screen
import spock.lang.Specification

import java.awt.*

class Test_ScreenFactory extends Specification {


    def "Test getScreen"() {

        given:
        def screenF = Mock(ScreenFactory)

        when:

        Screen screen = screenF.getScreen(new Dimensions(20, 30), 10)
        Font font = screenF.loadFont(3)

        then:
        1 * screenF.getScreen(_, _)
        1 * screenF.loadFont(_)


    }

}