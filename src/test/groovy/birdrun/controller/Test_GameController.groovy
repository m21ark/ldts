package birdrun.controller

import birdrun.controller.arena.ArenaController
import birdrun.model.Dimensions
import com.googlecode.lanterna.screen.Screen
import spock.lang.Specification

class Test_GameController extends Specification {


    def "Test get/set Ticks"(int x, int y) {

        given:

        Screen screen = Mock(Screen)
        GameController gc = GameController.getInstance(new Dimensions(3, 3), screen)

        when:
        gc.setGameLoopInt(x)
        gc.setResetCountGameLoop(y)

        then:
        gc.getGameLoopInt() == x
        gc.getResetCountGameLoop() == y

        where:
        x << [-10, 0, -1, -2, 3, 6, 8, 0, 123, 34, 15, 8]
        y << [8, 0, 123, 34, 15, 8, -10, 0, -1, -2, 3, 6]
    }

    def "Test gameTick"(int x, int y) {

        given:

        Screen screen = Mock(Screen)
        GameController gc = GameController.getInstance(new Dimensions(3, 3), screen)

        when:
        gc.setGameLoopInt(x)
        gc.setResetCountGameLoop(y)
        gc.gameTick()

        then:
        if (x == 170) {
            gc.getGameLoopInt() == 0
            gc.getResetCountGameLoop() == y + 1

        } else {
            gc.getGameLoopInt() == x + 1
        }

        if (y % 20 == 0) {
            gc.getGameLoopInt() == 0
            gc.getResetCountGameLoop() == y + 1
        }

        where:
        x << [6, 170, 12, 3, 6, 24, 120, 170, 170, 8, 0, 123, 34, 15, 8]
        y << [8, 0, 170, 12, 20, 80, 123, 34, 20, 15, 8, 100, 0, 3, 90]


    }


    def "Test new instance"() {

        given:

        Screen screen = Mock(Screen)
        GameController gc = GameController.getInstance(new Dimensions(3, 3), screen)

        when:

        gc.instantiated()
        gc.removeInstance()

        then:

        !gc.instantiated()

    }


    def "Test MusicPlaying"() {

        given:

        Screen screen = Mock(Screen)
        GameController gc = GameController.getInstance(new Dimensions(3, 3), screen)

        when:
        !gc.isMusicPlaying()
        gc.testMusicPlaying()

        then:
        gc.isMusicPlaying()

    }

    def "Test getArena"() {

        given:

        Screen screen = Mock(Screen)
        GameController gc = GameController.getInstance(new Dimensions(3, 3), screen)

        when:

        ArenaController ac = gc.getArena()

        then:

        ac != null

    }


}

