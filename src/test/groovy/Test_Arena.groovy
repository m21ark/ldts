import spock.lang.Specification

class Test_Arena extends Specification {
    int width, height
    Bird bird
    Arena arena

    def setup() throws Exception {
        width = 50
        height = 30
        bird = new Bird(new Position(width / 2 as int, height / 2 as int), 'B' as Character, "#000000")
        arena = new Arena(width, height, bird)
    }

    def "Test createMatrix"() {
        when:
        Matrix matrix = arena.createMatrix(width, height, ' ' as Character)
        then:
        matrix.getPos(0,0).getChar() == arena.borderChar
        matrix.getPos(width-1,0).getChar() == arena.borderChar
        matrix.getPos(0,height-1).getChar() == arena.borderChar
        matrix.getPos(width-1,height-1).getChar() == arena.borderChar
    }

    def "Test canBirdMove"() {
        given:
        Position newPos = new Position(5, 7)
        when:
        boolean canMove = arena.canBirdMove(newPos)
        Matrix matrix = arena.getMatrix()
        Character newPosChar = matrix.getPos(newPos).getChar()
        then:
        if (newPosChar == arena.coinChar || newPosChar == ' ') canMove else !canMove
    }

    def "Test moveBird"() {
        given:
        Position pos = new Position(3, 4)
        when:
        arena.moveBird(pos)
        then:
        bird.getPosition() == pos

    }

    def "Test playerAlive"() {
        given:
        boolean birdStatus = bird.isAlive()
        when:
        boolean alive = arena.playerAlive()
        then:
        birdStatus == alive
    }

    def "Test detectCollision"() {
        //TODO
    }

    def "Test applyGravity"() {
        //TODO
    }

    def "Test matrixUpdate"() {
        //TODO
    }

    def "Test addRandomElem"() {
        //TODO
    }

}