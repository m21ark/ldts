package birdrun.viewer.menu

import birdrun.model.Dimensions
import com.googlecode.lanterna.graphics.TextGraphics
import spock.lang.Specification

class Test_InitialMenuViewer extends Specification {
    def "Test Initial_MenuViewer"() {
        given:
        def menuViewer = new Initial_MenuViewer(new Dimensions(30, 20), "#00FF00", "#FF00FF")
        def graphics = Mock(TextGraphics)

        when:
        menuViewer.draw(graphics)

        then:
        1 * graphics.setBackgroundColor(_)
        1 * graphics.fillRectangle(_, _, _)
        1 * graphics.enableModifiers(_)
        2 * graphics.setForegroundColor(_)
        4 * graphics.putString(_, _)

    }
}
