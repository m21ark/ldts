package birdrun.viewer

import birdrun.model.ArenaModel
import com.googlecode.lanterna.graphics.TextGraphics
import com.googlecode.lanterna.screen.Screen
import spock.lang.Specification

class Test_GameViewer extends Specification {

    def "Test draw"() {

        given:
        def gameViewer = new GameViewer()
        def graphics = Mock(TextGraphics)
        def screen = Mock(Screen)
        def arenaModel = Mock(ArenaModel)
        def arenaViewer = Mock(ArenaViewer)

        when:

        gameViewer.draw(screen, graphics, arenaModel, arenaViewer)

        then:
        1 * screen.clear()
        1 * screen.refresh()
        1 * arenaViewer.draw(graphics, _, _, _)
        1 * arenaModel.getMatrix()
        1 * arenaModel.getPlayerHp()
        1 * arenaModel.getPlayerScore()

    }

}