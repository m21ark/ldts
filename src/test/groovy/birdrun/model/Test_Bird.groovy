package birdrun.model

import birdrun.model.Bird
import birdrun.model.Position
import com.googlecode.lanterna.SGR
import com.googlecode.lanterna.TerminalPosition
import com.googlecode.lanterna.TextColor
import com.googlecode.lanterna.graphics.TextGraphics
import spock.lang.Specification

class Test_Bird extends Specification {
    Bird bird
    int x0, y0

    def setup() throws Exception {
        x0 = 7
        y0 = 10
        bird = new Bird(x0, y0, 'B' as Character, "#00FF00")
    }

    def "Test draw"() {
        given:
        def graphics = Mock(TextGraphics)
        when:
        bird.draw(graphics)

        then:

        1 * graphics.setForegroundColor(TextColor.Factory.fromString(bird.color))
        1 * graphics.enableModifiers(SGR.BOLD)
        1 * graphics.putString(new TerminalPosition(bird.position.getX(), bird.position.getY()), bird.character.toString())

    }

    def "Test get char"() {
        when:
        Character c1 = bird.getChar()

        then:
        c1 == 'B'
    }

    def "Test takeDamage"(int n) {
        given:
        int hp = bird.getHp()

        when:
        bird.takeDamage(n)
        then:

        bird.getHp() == hp - n

        where:
        n << [-10, 0, -1, -2, 3, 6, 8, 0, 123, 34, 15, 8]
    }

    def "Test takeDamage"() {
        given:
        int hp = bird.getHp()

        when:
        bird.takeDamage()
        then:
        bird.getHp() == hp - 1

    }

    def "Test equality"() {
        when:
        Bird bird2 = new Bird(new Position(x0, y0), 'B' as Character, "#00FF00")
        Bird bird3 = new Bird(new Position(20, 30), 'B' as Character, "#00FF00")
        then:
        bird2 == bird
        bird3 != bird

    }

    def "Test get/set Hp"(int givenHp) {
        given:
        int hp = givenHp
        when:
        bird.setHp(hp)
        then:
        givenHp == bird.getHp()

        where:
        givenHp << [-10, 0, -1, -2, 3, 6, 8, 0, 123, 34, 15, 8]
    }

    def "Test setCoinCount"(int givenCoinCount) {

        when:
        bird.setCoinCount(givenCoinCount)
        then:
        givenCoinCount == bird.getCoinCount()

        where:
        givenCoinCount << [-10, 0, -1, -2, 3, 6, 8, 0, 123, 34, 15, 8]
    }

    def "Test addHp"(int deltaHp) {
        given:
        int initialHp = bird.getHp()
        when:
        bird.addHp(deltaHp)
        then:
        bird.getHp() == initialHp + deltaHp

        where:
        deltaHp << [-10, 0, -1, -2, 3, 6, 8, 0, 123, 34, 15, 8]

    }

    def "Test setStamina"(int stamina) {
        when:
        bird.setStamina(stamina)
        then:

        if (stamina > 200) {
            bird.setStamina(200)
            bird.getStamina() == 200
        } else if (stamina < 0) {
            bird.setStamina(0)
            bird.getStamina() == 0
        } else stamina == bird.getStamina()


        where:
        stamina << [-10, -67, 0, -1, -2, 3, 0, 123, 503, 199, 200, 201, 8]

    }

    def "Test isAlive"(int givenHp) {
        given:
        bird.setHp(givenHp)
        when:
        int hp = bird.getHp()
        boolean alive = bird.isAlive()
        then:

        if (alive) hp > 0 else hp <= 0

        where:
        givenHp << [-10, 0, -1, -2, 3, 6, 8, 0, 123, 34, 15, 8]

    }

    def "Test pickCoin"(int numCoins) {
        given:
        int coinCount = bird.getCoinCount()
        when:
        int newCoinCount = bird.pickCoin(numCoins)
        bird.setCoinCount(newCoinCount)
        then:
        bird.getCoinCount() == coinCount + numCoins

        where:
        numCoins << [-10, 0, -1, -2, 3, 6, 8, 0, 123, 34, 15, 8]
    }

    def "Test moveUp"(int delta) {
        given:
        Position pos = bird.getPosition()
        when:
        Position newPos = bird.moveUp(delta)
        then:
        newPos.getY() == pos.getY() - delta

        where:
        delta << [-10, 0, -1, -2, 3, 6, 8, 0, 123, 34, 15, 8]
    }

    def "Test moveDown"(int delta) {
        given:
        Position pos = bird.getPosition()

        when:
        Position newPos = bird.moveDown(delta)
        then:
        newPos.getY() == pos.getY() + delta

        where:
        delta << [-10, 0, -1, -2, 3, 6, 8, 0, 123, 34, 15, 8]
    }

    def "Test moveLeft"(int delta) {
        given:
        Position pos = bird.getPosition()
        when:
        Position newPos = bird.moveLeft(delta)
        then:
        newPos.getX() == pos.getX() - delta
        where:
        delta << [-10, 0, -1, -2, 3, 6, 8, 0, 123, 34, 15, 8]
    }

    def "Test moveRight"(int delta) {
        given:
        Position pos = bird.getPosition()
        when:
        Position newPos = bird.moveRight(delta)
        then:
        newPos.getX() == pos.getX() + delta
        where:
        delta << [-10, 0, -1, -2, 3, 6, 8, 0, 123, 34, 15, 8]
    }

    def "Test updateColor"(String newColor) {
        when:
        bird.updateColor(newColor)
        then:
        newColor == bird.color

        where:
        newColor << ["#FFFFFF", "#FFFF00", "#000000", " ", ""]
    }
}