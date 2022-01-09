import spock.lang.Specification

class Test_Block extends Specification {


    def "Test move"() {
        given:

        def block = new Block(x0, y0, 'X' as Character, "#00FF00")

        when:
        block.move(deltaX, deltaY)

        int newX = block.getPositionX()
        int newY = block.getPositionY()

        then:
        newX == x0 + deltaX
        newY == y0 + deltaY

        where:
        x0 << [-10, 0, -1, 0, 1, 2, 4, -46, 7, 78, -15, 9999]
        y0 << [10, 9, 1, -2, 3, 6, 8, 0, 123, 34, -15, 8]

        deltaX << [-6, 0, -1, -2, 3, 6, 8, 0, -123, 4, 15, 8]
        deltaY << [4, 46, 7, 78, -15, -9999, 8, 0, 26, 34, 21, 14]

    }


    def "Test get char"() {
        given:

        def block = new Block(x0, y0, 'X' as Character, "#00FF00")

        when:
        Character c1 = block.getChar()

        then:
        c1 == 'X'

        where:
        x0 << [-10, 0, -1, 0, 1, 2, 4, -46, 7, 78, -15, 9999]
        y0 << [10, 9, 1, -2, 3, 6, 8, 0, 123, 34, -15, 8]
    }

    def "Test equality"() {
        given:

        def block = new Block(x0, y0, 'X' as Character, "#00FF00")
        when:
        Block block2 = new Block(new Position(x0, y0), 'X' as Character, "#00FF00")
        then:
        block == block2

        where:
        x0 << [-10, 0, -1, 0, 1, 2, 4, -46, 7, 78, -15, 9999]
        y0 << [10, 9, 1, -2, 3, 6, 8, 0, 123, 34, -15, 8]
    }

    def "Test gravityMoveDown"() {
        given:
        def block = new Block(x0, y0, 'X' as Character, "#00FF00")

        when:
        block.gravityMove()

        then:
        block.getPosition().getX() == x0
        block.getPosition().getY() == y0 + 1

        where:
        x0 << [-10, 0, -1, 0, 1, 2, 4, -46, 7, 78, -15, 9999]
        y0 << [10, 9, 1, -2, 3, 6, 8, 0, 123, 34, -15, 8]
    }

}