package birdrun.controller

import birdrun.controller.arena.ArenaController
import birdrun.model.*
import birdrun.state.Command
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

    def "Test BirdStartPos"() {

        when:
        arena = new ArenaController(new Dimensions(width, height))

        then:
        arena.getArenaModel().getBird().getPosition() == new Position(width / 2 as int, height / 2 as int)

    }

    def "Test randInt"() {

        given:
        int rand

        when:
        if (x < y) rand = ArenaController.randInt(x, y) else rand = -1

        then:
        if (x < y) {
            rand != 0
            rand >= x
            rand < y
        } else rand = -1

        where:
        x << [-16, 0, -1, 0, 1, 2, 4, 46, 7, 78, 15, 99]
        y << [-10, 0, -1, -2, 3, 6, 8, 0, 123, 34, 15, 8]

    }


    def "Test Getters"() {

        when:
        def model = arena.getArenaModel()
        def viewer = arena.getArenaViewer()
        int score = arena.getPlayerScore()
        boolean alive = arena.playerAlive()

        then:
        model != null
        viewer != null
        if (alive) score > 0
    }


    def "Test isCollectable"() {

        given:
        def bird = new Bird(new Position(1, 1), 'B' as Character, "#00FF00")
        def block = new Block(new Position(1, 1), 'X' as Character, "#00FF00")
        def coin = new Coin(new Position(1, 1), 'C' as Character, "#00FF00")
        def life = new Extra_Life(new Position(1, 1), 'L' as Character, "#00FF00")

        when:
        boolean b1 = ArenaController.isCollectable(bird)
        boolean b2 = ArenaController.isCollectable(block)
        boolean b3 = ArenaController.isCollectable(coin)
        boolean b4 = ArenaController.isCollectable(life)

        then:
        !b1
        !b2
        b3
        b4

    }


    def "Test chooseMatrixCol"() {

        when:
        int col = arena.chooseMatrixCol(0)

        then:
        int index = arena.getArenaModel().getMatrix().getSmallerCol(arena.blockChar)
        col == index + 1

    }

    def "Test inBorderBird"() {

        given:
        Position pos = new Position(x, y)

        when:
        boolean inBorder = arena.inBorderBird(pos)

        then:
        inBorder == !(pos.getX() < width - 1 && pos.getX() > 0 && pos.getY() < height - 1 && pos.getY() > 3)

        where:
        x << [-16, 0, -1, 0, 1, 2, 23, 46, 7, 78, 15, 99]
        y << [-10, 8, 1, -2, 3, 6, 18, 0, 123, 34, 15, 8]

    }


    def "Test canBirdMove"() {

        given:
        Position pos = new Position(x, y)

        when:
        boolean inBorder = arena.inBorderBird(pos)
        boolean canMove = arena.canBirdMove(pos)
        Element newPos = arena.getArenaModel().matrixGetPos(pos)

        then:
        if (inBorder) !canMove
        if (newPos == null) !canMove else if (newPos.getChar() == arena.blockChar) !canMove else true

        where:
        x << [-16, 0, -1, 0, 1, 2, 23, 46, 7, 78, 15, 99]
        y << [-10, 8, 1, -2, 3, 6, 18, 0, 123, 34, 15, 8]
    }

    def "Test moveBird"() {

        given:
        Position pos = new Position(x, y)

        when:

        boolean moved = arena.moveBird(pos)

        Element newPos = arena.getArenaModel().matrixGetPos(pos)


        then:

        if (arena.canBirdMove(pos)) {
            moved
            if (newPos != null) newPos.getChar() == arena.birdChar
        } else {
            !moved
        }

        where:
        x << [-16, 0, -1, 0, 1, 2, 23, 46, 7, 78, 15, 99]
        y << [-10, 8, 1, -2, 3, 6, 18, 0, 123, 34, 15, 8]

    }

    def "Test birdFly"(int stamina) {

        given:
        Bird bird = arena.getArenaModel().getBird()

        int x = bird.getPositionX()
        int y = bird.getPositionY()
        bird.setStamina(stamina)

        when:
        arena.birdFly(bird)
        Bird newBird = arena.getArenaModel().getBird()
        int newStamina = newBird.getStamina()
        int newX = bird.getPositionX()
        int newY = bird.getPositionY()

        then:
        if (stamina > 20) {
            newStamina == stamina - 11
            newY == y + 1
        } else {
            newStamina == stamina
            newY == y
        }

        x == newX

        where:
        stamina << [-16, 0, -1, 0, 1, 2, 23, 46, 7, 78, 15, 99]

    }


    def "Test ExecuteCommand"() {

        given:
        Position birdPos = arena.getArenaModel().getBird().getPosition()

        when:
        boolean b = arena.executeCommand(command)

        then:
        if (command == null) {
            b
        } else if (command == Command.COMMAND.PAUSE) {
            !b
        } else if (command == Command.COMMAND.UP) {

            Position newPos = arena.getArenaModel().getBird().getPosition()
            newPos.getY() == birdPos.getY() + 1
            newPos.getX() == birdPos.getX()

        } else if (command == Command.COMMAND.RIGHT) {
            true
        } else if (command == Command.COMMAND.LEFT) {
            true
        } else b

        where:
        command << [null, Command.COMMAND.NONE,
                    Command.COMMAND.UP,
                    Command.COMMAND.LEFT,
                    Command.COMMAND.RIGHT,
                    Command.COMMAND.PAUSE]

    }


}

