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
        if (alive) score > 0 else !alive && score == 0
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

            Position newPos = arena.getArenaModel().getBird().getPosition()
            newPos.getY() == birdPos.getY()
            newPos.getX() == birdPos.getX() + 1

        } else if (command == Command.COMMAND.LEFT) {

            Position newPos = arena.getArenaModel().getBird().getPosition()
            newPos.getY() == birdPos.getY()
            newPos.getX() == birdPos.getX() - 1

        } else b

        where:
        command << [null, Command.COMMAND.NONE,
                    Command.COMMAND.UP,
                    Command.COMMAND.LEFT,
                    Command.COMMAND.RIGHT,
                    Command.COMMAND.PAUSE]

    }

    def "Test applyGravity Stamina"() {

        given:

        Bird bird = arena.getArenaModel().getBird()
        bird.setStamina(stamina)
        arena.getArenaModel().setBird(bird)

        when:
        arena.applyGravity()
        int newStam = arena.getArenaModel().getBird().getStamina()

        then:
        if (stamina < 0) newStam == 6 else newStam == stamina + 6

        where:
        stamina << [-16, 0, -1, 0, 1, 2, 23, 46, 7, 78, 15, 99]
    }

    def "Test canApplyGravityBlock"() {

        given:
        Position pos = new Position(8, 7)
        Block block = new Block(3, 6, ArenaController.blockChar as Character, "#00FF00")
        Coin coin = new Coin(3, 2, ArenaController.coinChar as Character, "#00FF00")
        Extra_Life life = new Extra_Life(3, 2, ArenaController.lifeChar as Character, "#00FF00")
        Bird bird = new Bird(3, 2, ArenaController.birdChar as Character, "#00FF00")

        when:
        boolean b1 = arena.canApplyGravityBlock(coin, pos)
        boolean b2 = arena.canApplyGravityBlock(block, pos)
        boolean b3 = arena.canApplyGravityBlock(life, pos)
        boolean b4 = arena.canApplyGravityBlock(bird, pos)

        then:
        b1 && b3 && b4
        !b2

    }


    def "Test canApplyGravityCollectable"() {

        given:
        Block block = new Block(3, 6, ArenaController.blockChar as Character, "#00FF00")
        Coin coin = new Coin(3, 2, ArenaController.coinChar as Character, "#00FF00")
        Extra_Life life = new Extra_Life(3, 2, ArenaController.lifeChar as Character, "#00FF00")
        Bird bird = new Bird(3, 2, ArenaController.birdChar as Character, "#00FF00")

        when:
        boolean c1 = arena.canApplyGravityCollectable(coin, coin.getChar())
        boolean c2 = arena.canApplyGravityCollectable(coin, block.getChar())
        boolean c3 = arena.canApplyGravityCollectable(coin, life.getChar())
        boolean c4 = arena.canApplyGravityCollectable(coin, bird.getChar())
        boolean c5 = arena.canApplyGravityCollectable(coin, ' ' as Character)

        boolean b1 = arena.canApplyGravityCollectable(life, coin.getChar())
        boolean b2 = arena.canApplyGravityCollectable(life, block.getChar())
        boolean b3 = arena.canApplyGravityCollectable(life, life.getChar())
        boolean b4 = arena.canApplyGravityCollectable(life, bird.getChar())
        boolean b5 = arena.canApplyGravityCollectable(life, ' ' as Character)

        then:
        !(c1 && c2 && c3)
        c4 && c5

        !(b1 && b2 && b3)
        b4 && b5

    }


}






