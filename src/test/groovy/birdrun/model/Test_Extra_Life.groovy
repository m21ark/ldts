package birdrun.model

import birdrun.model.Extra_Life
import birdrun.model.Position
import spock.lang.Specification

class Test_Extra_Life extends Specification {

    def "Test gravity"() {
        given:

        x1 = 5
        y1 = 8
        x2 = 9
        y2 = 7
        def life1 = new Extra_Life(x1, y1, 'L' as Character, "#00FF00")
        def life2 = new Extra_Life(new Position(x2, y2), 'L' as Character, "#00FF00")

        when:
        int newY1 = y1 + 1
        int newY2 = y2 + 1
        life1.gravityMove()
        life2.gravityMove()

        then:
        newY1 == life1.getPositionY()
        newY2 == life2.getPositionY()

        where:
        x1 << [-10, 0, -1, 0, 1, 2, 4, -46, 7, 78, -15, 101]
        y1 << [10, 9, 1, -2, 3, 6, 8, 0, 123, 34, -15, 8]
        x2 << [-10, 0, -1, 0, 1, 2, 4, 46, 7, 78, 15, 99]
        y2 << [-10, 0, -1, -2, 3, 6, 8, 0, 123, 34, 15, 8]
    }

    def "Test get char"() {
        given:
        def life1 = new Extra_Life(x1, y1, 'L' as Character, "#00FF00")
        def life2 = new Extra_Life(new Position(x2, y2), 'L' as Character, "#00FF00")

        when:
        Character c1 = life1.getChar()
        Character c2 = life2.getChar()

        then:
        c1 == 'L'
        c2 == 'L'

        where:
        x1 << [-10, 0, -1, 15, 101]
        y1 << [0, 123, 34, -15, 8]
        x2 << [-10, 0, -1, 67, 99]
        y2 << [3, 6, 8, 0, 123]
    }

    def "Test equality"() {
        given:
        def life1 = new Extra_Life(x1, y1, 'L' as Character, "#00FF00")
        def life2 = new Extra_Life(new Position(x2, y2), 'L' as Character, "#00FF00")

        when:
        Position pos1 = life1.getPosition()
        Position pos2 = life2.getPosition()
        Extra_Life life3 = new Extra_Life(new Position(x1, y1), 'L' as Character, "#00FF00")

        then:
        if (x1 == x2 && y1 == y2) pos1 == pos2 else pos1 != pos2
        life3 == life1

        where:
        x1 << [-10, 0, -1, 0, 1, 2, 4, -46, 7, 78, -15, 101]
        y1 << [10, 9, 1, -2, 3, 6, 8, 0, 123, 34, -15, 8]
        x2 << [-10, 0, -1, 0, 1, 2, 4, 46, 7, 78, 15, 99]
        y2 << [-10, 0, -1, -2, 3, 6, 8, 0, 123, 34, 15, 8]
    }


}