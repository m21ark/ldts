import com.googlecode.lanterna.graphics.TextGraphics
import spock.lang.Specification

class Test_MenuViewer extends Specification {


    def "Test drawLoadingScreen"() {

        given:

        def menuViewer = new MenuViewer(30, 20, "#00FF00", "#FF00FF")
        def graphics = Mock(TextGraphics)

        when:

        def  screen= menuViewer.drawLoadingScreen(graphics)

        then:
        1* graphics.setBackgroundColor(_)
        1* graphics.fillRectangle(_,_,_)
        1*graphics.enableModifiers(_)
        1*graphics.setForegroundColor(_)
        2*graphics.putString(_,_)

    }

    def "Test drawDeathScreen"() {

        given:

        def menuViewer = new MenuViewer(30, 20, "#00FF00", "#FF00FF")
        def graphics = Mock(TextGraphics)

        when:

        def  screen= menuViewer.drawDeathScreen(graphics, 0)

        then:
        1* graphics.setBackgroundColor(_)
        1* graphics.fillRectangle(_,_,_)
        1*graphics.enableModifiers(_)
        1*graphics.setForegroundColor(_)
        2*graphics.putString(_,_)

    }

}