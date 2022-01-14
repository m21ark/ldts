package birdrun.controller

import birdrun.controller.arena.ArenaController
import birdrun.model.Dimensions
import birdrun.model.Matrix
import spock.lang.Specification

class Test_MatrixFactory extends Specification {


    def "Test getMatrix"() {

        def width = 50
        def height = 30

        def arena = Mock(ArenaController)

        when:
        Matrix matrix = new MatrixFactory().getMatrix(new Dimensions(width, height), arena.borderChar, "#000000")

        then:
        matrix.getWidth() == width
        matrix.getHeight() == height
        matrix.getPos(0, 0).getChar() == arena.borderChar
        matrix.getPos(width - 1, 0).getChar() == arena.borderChar
        matrix.getPos(0, height - 1).getChar() == arena.borderChar
        matrix.getPos(width - 1, height - 1).getChar() == arena.borderChar
        matrix.getPos(width / 2 as int, height / 2 as int).getChar() == ' '

    }

}