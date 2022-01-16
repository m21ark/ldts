package birdrun.controller

import birdrun.controller.arena.ArenaController
import birdrun.controller.arena.ArenaUpdater
import birdrun.model.ArenaModel
import birdrun.model.Block
import birdrun.model.Dimensions
import birdrun.model.Matrix
import spock.lang.Specification

class Test_ArenaUpdater extends Specification {


    def "Test isMatrixBottomRowFull True"() {


        given:


        def dimensions = new Dimensions(2, 3)
        def arenaModel = Mock(ArenaModel)
        arenaModel.getDimensions() >> dimensions
        def arenaUpdater = new ArenaUpdater(arenaModel)

        def block1 = new Block(0, 0, ArenaController.blockChar, "#00FF00")
        def block2 = new Block(0, 1, ArenaController.blockChar, "#00FF00")
        def block3 = new Block(0, 2, ArenaController.blockChar, "#00FF00")

        def block4 = new Block(1, 0, ArenaController.blockChar, "#00FF00")
        def block5 = new Block(1, 1, ArenaController.blockChar, "#00FF00")
        def block6 = new Block(1, 2, ArenaController.blockChar, "#00FF00")

        Matrix matrix = new Matrix(dimensions, ' ' as Character)

        matrix.setPos(block1)
        matrix.setPos(block2)
        matrix.setPos(block3)
        matrix.setPos(block4)
        matrix.setPos(block5)
        matrix.setPos(block6)

        arenaModel.getMatrix() >> matrix

        when:

        boolean bool = arenaUpdater.isMatrixBottomRowFull()

        then:

        bool


    }

    def "Test isMatrixBottomRowFull False"() {

        given:

        def arenaModel = Mock(ArenaModel)
        def dimensions = new Dimensions(5, 8)
        arenaModel.getDimensions() >> dimensions

        def arenaUpdater = new ArenaUpdater(arenaModel)

        Matrix matrix = new Matrix(dimensions, ' ' as Character)


        arenaModel.getMatrix() >> matrix

        when:

        boolean bool = arenaUpdater.isMatrixBottomRowFull()

        then:

        !bool


    }

    def "Test updateBirdColor"(int stamina) {

        given:
        def arenaModel = Mock(ArenaModel)
        def dimensions = new Dimensions(5, 8)

        arenaModel.getDimensions() >> dimensions
        arenaModel.getBirdStamina() >> stamina

        def arenaUpdater = new ArenaUpdater(arenaModel)

        when:

        def birdColor = arenaUpdater.updateBirdColor()

        then:

        if (stamina > 100) birdColor == "#FFFFFF" else if (stamina < 50) birdColor = "#C51663" else if (stamina < 100) birdColor == "#BEC516"

        where:

        stamina << [-10, 0, 49, 51, 50, 100, -1, 1, 0, 101, 99, 12 - 13, 90, 300, 500]


    }


}

