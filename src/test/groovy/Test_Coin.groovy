import spock.lang.Specification

class Test_Coin extends Specification {
    Coin coin1
    Coin coin2
    int x1, y1, x2, y2

    def setup() throws Exception {
        x1 = 5
        y1 = 8
        x2 = 9
        y2 = 7
        coin1 = new Coin(x1, y1, 'C' as Character, "#00FF00")
        coin2 = new Coin(new Position(x2, y2), 'C' as Character, "#00FF00")
    }

    def "Test gravity"() {
        when:
        int newY1 = y1 + 1
        int newY2 = y2 + 1
        coin1.gravityMove()
        coin2.gravityMove()

        then:
        newY1 == coin1.getPositionY()
        newY2 == coin2.getPositionY()
    }

    def "Test get char"() {
        when:
        Character c1 = coin1.getChar()
        Character c2 = coin2.getChar()

        then:
        c1 == 'C'
        c2 == 'C'
    }

    def "Test equality"() {
        when:
        Position pos1 = coin1.getPosition()
        Position pos2 = coin2.getPosition()
        Coin coin3 = new Coin(new Position(x1, y1), 'C' as Character, "#00FF00")

        then:
        pos1 != pos2
        coin3 == coin1
    }


}