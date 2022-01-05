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