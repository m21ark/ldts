package birdrun.model

import spock.lang.Specification

class Test_Position extends Specification{

    Position posi
    int x0, y0

    def setup() throws Exception {
        x0 = 7
        y0 = 10
        posi = new Position(x0, y0)
    }

    def "Test set/get"(int x, int y){
        given:

        int nX = 10
        int nY = 20

        Position pos = new Position(x,y)

        when:

        int x0 = pos.getX()
        int y0 = pos.getY()

        pos.setX(nX)
        pos.setY(nY)

        then:

        x == x0
        y == y0

        pos.getX() == nX
        pos.getY() == nY

        where:

        x     | y
        1     | 15
        2     | 1
        10    | 1
        20    | 35
        -1    | 20
        0     | 90
        100   | -10
    }

    def "Test equals"(){
        when:
        Object o1 = new Object()
        Position o2 = new Position(x0,y0)
        Position o3 = new Position(20,30)
        Object o4 = null

        then:

        !posi.equals(o1)
        posi.equals(o2)
        !posi.equals(o3)
        posi.getY() == o2.getY() && posi.getX() == o2.getX()
        !posi.equals(o4)

    }


}
