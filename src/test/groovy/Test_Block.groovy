import spock.lang.Specification

class Test_Block extends Specification {
    Block block
    int x0, y0

    def setup() throws Exception {
        x0 = 7
        y0 = 10
        block = new  Block(x0,y0, 'X' as Character,"#00FF00")
    }

    def "Test move"() {
        //TODO test more initial positions and deltas
        when:
        int deltaX = 4
        int deltaY = -3
        block.move(deltaX,deltaY)

        int newX = block.getPositionX()
        int newY = block.getPositionY()

        then:
        newX == x0 + deltaX
        newY== y0 + deltaY
    }

    def "Test equality"(){
        when:
        Block block2 = new Block(new Position(x0,y0),'X' as Character,"#00FF00")
        then:
        block == block2
    }

}