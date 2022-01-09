import spock.lang.Specification

class Test_Coin extends Specification {

    def "Test gravity"() {
        given:

        x1 = 5
        y1 = 8
        x2 = 9
        y2 = 7
        def coin1 = new Coin(x1, y1, 'C' as Character, "#00FF00")
        def coin2 = new Coin(new Position(x2, y2), 'C' as Character, "#00FF00")

        when:
        int newY1 = y1 + 1
        int newY2 = y2 + 1
        coin1.gravityMove()
        coin2.gravityMove()

        then:
        newY1 == coin1.getPositionY()
        newY2 == coin2.getPositionY()

        where:
        x1 << [-10, 0, -1, 0, 1, 2, 4, -46, 7, 78, -15, 101]
        y1 << [10, 9, 1, -2, 3, 6, 8, 0, 123, 34, -15, 8]
        x2 << [-10, 0, -1, 0, 1, 2, 4, 46, 7, 78, 15, 99]
        y2 << [-10, 0, -1, -2, 3, 6, 8, 0, 123, 34, 15, 8]
    }

    def "Test get char"() {
        given:
        def coin1 = new Coin(x1, y1, 'C' as Character, "#00FF00")
        def coin2 = new Coin(new Position(x2, y2), 'C' as Character, "#00FF00")

        when:
        Character c1 = coin1.getChar()
        Character c2 = coin2.getChar()

        then:
        c1 == 'C'
        c2 == 'C'

        where:
        x1 << [-10, 0, -1, 15, 101]
        y1 << [0, 123, 34, -15, 8]
        x2 << [-10, 0, -1, 67, 99]
        y2 << [3, 6, 8, 0, 123]
    }

    def "Test equality"() {
        given:
        def coin1 = new Coin(x1, y1, 'C' as Character, "#00FF00")
        def coin2 = new Coin(new Position(x2, y2), 'C' as Character, "#00FF00")

        when:
        Position pos1 = coin1.getPosition()
        Position pos2 = coin2.getPosition()
        Coin coin3 = new Coin(new Position(x1, y1), 'C' as Character, "#00FF00")

        then:
        if (x1 == x2 && y1 == y2) pos1 == pos2 else pos1 != pos2
        coin3 == coin1

        where:
        x1 << [-10, 0, -1, 0, 1, 2, 4, -46, 7, 78, -15, 101]
        y1 << [10, 9, 1, -2, 3, 6, 8, 0, 123, 34, -15, 8]
        x2 << [-10, 0, -1, 0, 1, 2, 4, 46, 7, 78, 15, 99]
        y2 << [-10, 0, -1, -2, 3, 6, 8, 0, 123, 34, 15, 8]
    }


}