package birdrun.controller

import birdrun.controller.ScreenFactory
import birdrun.model.Dimensions
import com.googlecode.lanterna.TerminalSize
import com.googlecode.lanterna.screen.Screen
import com.googlecode.lanterna.terminal.Terminal
import spock.lang.Specification

import java.awt.*

class Test_ScreenFactory extends Specification {
    Dimensions dimensions

    def setup() {
        dimensions = new Dimensions(20, 30)
    }


    def "Test getScreen Mock"() {

        given:
        def screenF = Mock(ScreenFactory)

        when:

        Screen screen = screenF.getScreen(dimensions, 10)
        Font font = screenF.loadFont(3)

        then:
        1 * screenF.getScreen(_, _)
        1 * screenF.loadFont(_)


    }

    def "Test loadFont"() {

        given:
        def screenF = new ScreenFactory()

        when:
        Font font = screenF.loadFont(3)

        then:
        font != null


    }

    def "Test createScreen"() {

        given:
        def terminal = Stub(Terminal)

        terminal.getTerminalSize() >> new TerminalSize(4, 5)

        when:

        Screen screen = new ScreenFactory().createScreen(terminal)

        then:

        screen != null

        screen.getCursorPosition() == null

        screen.getTerminalSize() == terminal.getTerminalSize()


    }


}