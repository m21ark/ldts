import com.googlecode.lanterna.graphics.TextGraphics
import spock.lang.Specification

class Test_ArenaViewer extends Specification {

    def "Test drawMatrix"() {


        given:
        int width = 15
        int height = 10

        def arenaViewer = new ArenaViewer(width, height, "#00FF00", "#00FF00")
        def graphics = Mock(TextGraphics)
        def matrix = Mock(Matrix)

        when:

        arenaViewer.drawMatrix(graphics, matrix)

        then:

        (width * height) * matrix.getPos(_, _)


    }

    def "Test drawGame"() {


        given:
        int width = 15
        int height = 10

        def arenaViewer = new ArenaViewer(width, height, "#00FF00", "#00FF00")
        def graphics = Mock(TextGraphics)
        def matrix = Mock(Matrix)

        when:

        arenaViewer.drawGame(graphics, 1, 1, matrix)

        then:

        1 * graphics.setBackgroundColor(_)
        1 * graphics.fillRectangle(_, _, _)
        2 * graphics.setForegroundColor(_)
        2 * graphics.putString(_, _)


    }


}


