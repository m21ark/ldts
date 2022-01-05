import spock.lang.Specification

class Test_Bird extends Specification {
    Bird bird
    int x0, y0

    def setup() throws Exception {
        x0 = 7
        y0 = 10
        bird = new  Bird(x0,y0, 'B' as Character,"#00FF00")
    }

    def "Test takeDamage"() {
        given:
        bird.setHp(20)
        int hp = bird.getHp()

        when:
        bird.takeDamage()
        then:
        hp-1 == bird.getHp()
    }

    def "Test equality"(){
        when:
        Bird bird2 = new Bird(new Position(x0,y0), 'B' as Character, "#00FF00")
        then:
        bird2 == bird

    }

    def "Test get/set Hp"() {
        given:
        int hp = 10
        when:
        bird.setHp(hp)
        then:
        hp == bird.getHp()
    }

    def "Test setCoinCount"() {
        given:
        int num = 7
        when:
        bird.setCoinCount(num)
        then:
        num == bird.getCoinCount()
    }

    def "Test addHp"() {
        given:
        int initialHp = bird.getHp()
        int deltaHp = 4
        when:
        bird.addHp(deltaHp)
        then:
        bird.getHp() == initialHp+deltaHp
    }

    def "Test isAlive"(){
        given:
        bird.setHp(5)
        int hp = bird.getHp()
        when:
        boolean alive = bird.isAlive()
        then:

        if(!alive)
            hp>0
        else
            hp==0


    }

    def "Test pickCoin"(){
        given:
        int numCoins = 3
        int coinCount = bird.getCoinCount()
        when:
        bird.pickCoin(numCoins)
        then:
        bird.getCoinCount() == coinCount + numCoins
    }

    def "Test moveUp"(){
        given:
        Position pos = bird.getPosition()
        int delta = 2
        when:
        Position newPos = bird.moveUp(delta)
        then:
        newPos.getY() == pos.getY() - delta


    }

    def "Test moveDown"(){
        given:
        Position pos = bird.getPosition()
        int delta = 2
        when:
        Position newPos =bird.moveDown(delta)
        then:
        newPos.getY() == pos.getY() + delta
    }

    def "Test moveLeft"(){
        given:
        Position pos = bird.getPosition()
        int delta = 2
        when:
        Position newPos =bird.moveLeft(delta)
        then:
        newPos.getX() == pos.getX() - delta
    }

    def "Test moveRight"(){
        given:
        Position pos = bird.getPosition()
        int delta = 2
        when:
        Position newPos =bird.moveRight(delta)
        then:
        newPos.getX() == pos.getX() + delta
    }

}