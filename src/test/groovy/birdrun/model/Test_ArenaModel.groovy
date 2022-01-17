package birdrun.model

import birdrun.model.ArenaModel
import birdrun.model.Bird
import birdrun.model.Dimensions
import birdrun.model.Matrix
import spock.lang.Specification

class Test_ArenaModel extends Specification {
    Matrix matrix
    Bird bird
    ArenaModel arenaModel
    Dimensions dimensions


    def setup() throws Exception {
        matrix = Mock(Matrix)
        bird = Mock(Bird)
        dimensions = new Dimensions(20, 25)
        arenaModel = new ArenaModel(dimensions, matrix, "#00FF00")
        arenaModel.setBird(bird)
        arenaModel.setMatrix(matrix)

    }


    def "Test getPlayerScore"(int expectedScore) {
        given:
        bird.getCoinCount() >> expectedScore

        when:
        def score = arenaModel.getPlayerScore()

        then:
        score == expectedScore

        where:
        expectedScore << [-10, 0, -1, 0, 1, 2, 4, 46, 7, 78, 15, 9999]

    }

    def "Test getPlayerHp"(int expectedHp) {
        given:
        bird.getHp() >> expectedHp

        when:
        def hp = arenaModel.getPlayerHp()

        then:
        hp == expectedHp

        where:
        expectedHp << [-10, 0, -1, 0, 1, 2, 4, 46, 7, 78, 15, 9999]
    }


    def "Test setBirdColor"(String newColor) {

        given:

        def am = new ArenaModel(dimensions, matrix, "#00FF00")

        when:
        am.setBirdColor(newColor)

        then:
        newColor == am.getBird().color

        where:
        newColor << ["#FFFFFF", "#FFFF00", "#000000", " ", ""]
    }

    def "Test setBirdPos"(int x, int y) {

        given:

        Position pos = new Position(x, y)
        Bird bird = new Bird(1, 1, 'B' as Character, "#00FF00")
        def am = new ArenaModel(dimensions, matrix, "#00FF00")

        when:
        am.setBird(bird)
        am.setBirdPos(pos)

        then:
        bird.getPosition().getX() == pos.getX() && bird.getPosition().getY() == pos.getY()

        where:
        x << [-16, 0, -1, 0, 1, 2, 23, 46, 7, 78, 15, 99]
        y << [-10, 8, 1, -2, 3, 6, 18, 0, 123, 34, 15, 8]

    }


    def "Test getBirdStamina"() {

        given:
        Bird newBird = new Bird(1, 1, 'B' as Character, "#00FF00")
        arenaModel.setBird(newBird)

        when:
        int stamina1 = arenaModel.getBirdStamina()
        int stamina2 = arenaModel.getBird().getStamina()

        then:
        stamina1 == stamina2
    }

    def "Test addPlayerHp(int hp)"() {

        given:
        Bird newBird = new Bird(1, 1, 'B' as Character, "#00FF00")
        arenaModel.setBird(newBird)
        int hp0 = arenaModel.getBird().getHp()

        when:
        arenaModel.addPlayerHp(deltaHp)

        then:
        hp0 + deltaHp == arenaModel.getBird().getHp()

        where:
        deltaHp << [-10, 0, -1, -2, 3, 6, 8, 0, 123, 34, 15, 8]

    }

}


