package birdrun.model


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

    def "Test indexOfSmallest"() {

        given:
        def arr = new int[5]

        arr[0] = 3
        arr[1] = 5
        arr[2] = 7
        arr[3] = 2
        arr[4] = 9

        when:

        int index = matrix.indexOfSmallest(arr as ArrayList<Integer>)

        then:

        index == 3


    }

    def "Test  countLine"() {

        given:

        Character blockChar = 'X'
        Character coinChar = 'C'

        def block = new Block(1, 1, blockChar as Character, "#00FF00")
        def coin = new Coin(1, 1, coinChar as Character, "#00FF00")

        List<Element> list = new ArrayList<>()

        int count = 10

        when:

        for (int i = 0; i < count; i++) list.add(block)

        for (int i = 0; i < count / 2 + 1; i++) list.add(coin)

        int result = matrix.countLine(blockChar, list)

        then:

        result == count

    }


}

