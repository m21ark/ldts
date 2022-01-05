import spock.lang.Specification

class Test_Element extends Specification {
    Element el1, el2
    Character ch1, ch2

    def setup() throws Exception {
        ch1 = 'a'
        ch2 = 'b'
        el1 = new Element(4, 9, ch1, "#000000")
        el2 = new Element(8, 3, ch2, "#12FF9A")
    }

    def "Test gravityMoveDown"() {
        when:
        el1.gravityMoveDown()
        el2.gravityMoveDown()

        then:
        Position pos1 = new Position(4, 10)
        Position pos2 = new Position(8, 4)
        el1.getPosition() == pos1
        el2.getPosition() == pos2


    }

    def "Test get char"() {
        when:
        Character c1 = el1.getChar()
        Character c2 = el2.getChar()


        then:
        c1 == ch1
        c2 == ch2
        c1 != ch2
        ch1 != ch2
        c1 != ch2

    }

    def "Test fixedPos"() {
        given:
        Element el3 = new Element(new Position(4,5), ch2, "#12FF9A")
        when:
        el1.setFixedPos(true)
        then:
        !el3.isFixedPos()
        el1.isFixedPos()
        el3 != el1


    }

}