package birdrun.model

import birdrun.model.Bird
import birdrun.model.Block
import birdrun.model.Coin
import birdrun.model.Dimensions
import birdrun.model.Element
import birdrun.model.Matrix
import spock.lang.Specification

class Test_Matrix extends Specification {
    Coin coin1, coin2
    Block block1, block2, block3
    Bird bird
    int width, height
    Matrix matrix

    def setup() throws Exception {

        coin1 = new Coin(1, 2, 'C' as Character, "#00FF11")
        coin2 = new Coin(6, 20, 'C' as Character, "#00FF11")
        block1 = new Block(7, 10, 'X' as Character, "#11FF00")
        block2 = new Block(19, 8, 'X' as Character, "#11FF00")
        block3 = new Block(-1, 17, 'X' as Character, "#11FF00")
        bird = new Bird(5, 5, 'B' as Character, "#10AF20")

        width = 20
        height = 20
        matrix = new Matrix(new Dimensions(width, height), '.' as Character)

    }

    def "Test set/get Pos"() {
        when:
        boolean b1 = matrix.setPos(coin1)
        boolean b2 = matrix.setPos(coin2)
        boolean b3 = matrix.setPos(block1)
        boolean b4 = matrix.setPos(block2)
        boolean b5 = matrix.setPos(block3)
        boolean b6 = matrix.setPos(bird)


        then:
        false == (b2 || b5)
        true == (b1 && b3 && b4 && b6)

        Element elem1 = matrix.getPos(1, 2)
        Element elem3 = matrix.getPos(7, 10)
        Element elem4 = matrix.getPos(19, 8)
        Element elem6 = matrix.getPos(5, 5)

        elem1.getChar() == 'C'
        elem3.getChar() == 'X'
        elem4.getChar() == 'X'
        elem6.getChar() == 'B'
    }

    def "Test getDimensions"() {
        when:
        int height_ = matrix.getHeight()
        int width_ = matrix.getWidth()

        then:
        height_ == height
        width_ == width

    }

    def "Test getMatrix"() {
        when:
        def tempM = matrix.getMatrix()

        then:
        tempM.size() == height
        tempM.get(0).size() == width

    }


}

