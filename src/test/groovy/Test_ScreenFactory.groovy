import spock.lang.Specification

class Test_ScreenFactory extends Specification {


    def "Test getScreen"() {

        given:
        def screenF = Mock(ScreenFactory)

        when:

        def screen = screenF.getScreen(20, 30, 10)

        then:
        1 * screenF.getScreen(_, _, _)

    }

}