package birdrun.controller

import birdrun.controller.ScreenFactory
import birdrun.model.Dimensions
import spock.lang.Specification

class Test_ScreenFactory extends Specification {


    def "Test getScreen"() {

        given:
        def screenF = Mock(ScreenFactory)

        when:

        def screen = screenF.getScreen(new Dimensions(20, 30), 10)

        then:
        1 * screenF.getScreen(_,  _)

    }

}