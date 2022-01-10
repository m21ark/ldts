package birdrun.controller

import birdrun.controller.ArenaController
import birdrun.model.Bird
import birdrun.model.Dimensions
import birdrun.model.Matrix
import birdrun.model.Position
import spock.lang.Specification


class Test_ArenaController extends Specification {
    int width, height
    Bird bird
    ArenaController arena

    def setup() throws Exception {
        width = 50
        height = 30
        bird = new Bird(new Position(width / 2 as int, height / 2 as int), 'B' as Character, "#000000")
        arena = new ArenaController(new Dimensions(width, height))
    }

    def "Test canBirdMove"(int x, int y) {
        given:
        Position newPos = new Position(x, y)
        when:
        boolean canMove = arena.canBirdMove(newPos)
        Matrix matrix = arena.getArenaModel().getMatrix()

        if (matrix.getPos(newPos) == null) return

        Character newPosChar = matrix.getPos(newPos).getChar()
        then:
        if (newPosChar == arena.coinChar || newPosChar == ' ') canMove else !canMove

        where:
        x << [-10, 0, -1, 0, 1, 2, 4, 46, 7, 78, 15, 9999]
        y << [-10, 0, -1, -2, 3, 6, 8, 0, 123, 34, 15, 8]

    }

    def "Test moveBird"(int x, int y) {
        given:
        Position pos = new Position(x, y)
        when:
        arena.moveBird(pos)
        then:
        if (arena.canBirdMove(pos)) bird.getPosition() == pos

        where:
        x << [-10, 0, -1, 0, 1, 2, 4, 46, 7, 78, 15, 9999]
        y << [-10, 0, -1, -2, 3, 6, 8, 0, 123, 34, 15, 8]

    }

    def "Test playerAlive"() {
        given:
        boolean birdStatus = bird.isAlive()
        when:
        boolean alive = arena.playerAlive()
        then:
        birdStatus == alive
    }


}

