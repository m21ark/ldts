import spock.lang.Specification


class Test_ArenaController extends Specification {
    int width, height
    Bird bird
    ArenaController arena

    def setup() throws Exception {
        width = 50
        height = 30
        bird = new Bird(new Position(width / 2 as int, height / 2 as int), 'B' as Character, "#000000")
        arena = new ArenaController(width, height)
    }

    def "Test canBirdMove"() {
        given:
        Position newPos = new Position(5, 7)
        when:
        boolean canMove = arena.canBirdMove(newPos)
        Matrix matrix = arena.getArenaModel().getMatrix()
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
        if (arena.canBirdMove(pos)) bird.getPosition() == pos

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

