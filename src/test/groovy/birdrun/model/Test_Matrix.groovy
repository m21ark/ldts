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
        boolean b7 = matrix.setPos(null)

        Coin coin = new Coin(x, y, 'C' as Character, "#00FF11")
        boolean b8 = matrix.setPos(coin)

        boolean inBorder = x < 0 || y < 0 || x >= width || y >= height


        then:
        !b7
        !(b2 || b5)
        (b1 && b3 && b4 && b6)

        if (inBorder) b8 else matrix.getPos(x, y) == null

        Element elem1 = matrix.getPos(1, 2)
        Element elem3 = matrix.getPos(7, 10)
        Element elem4 = matrix.getPos(19, 8)
        Element elem6 = matrix.getPos(5, 5)

        elem1.getChar() == 'C'
        elem3.getChar() == 'X'
        elem4.getChar() == 'X'
        elem6.getChar() == 'B'

        where:
        x << [50, 100, 500, -1, -10, -200, 0, 3]
        y << [-1, -10, -200, 0, 3, 50, 100, 500]
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

        List<Integer> arr1 = [4, 5, 1, 6, 7, 8]
        List<Integer> arr2 = [4, -1, 0, 6, 10, 8]
        List<Integer> arr3 = [0, 5, 1, 6, -7, 1]
        List<Integer> arr4 = [0, 5, 1, 6, -7, -10]
        List<Integer> arr5 = []

        when:

        int index1 = matrix.indexOfSmallest(arr1)
        int index2 = matrix.indexOfSmallest(arr2)
        int index3 = matrix.indexOfSmallest(arr3)
        int index4 = matrix.indexOfSmallest(arr4)
        int index5 = matrix.indexOfSmallest(arr5)

        then:

        index1 == 2
        index2 == 1
        index3 == 4
        index4 == 5
        index5 == -1


    }

    def "Test Transpose"() {

        given:
        List<List<Integer>> m = [[1, 2, 3],
                                 [4, 5, 6]]

        when:

        m = matrix.transpose(m)

        then:

        m == [[1, 4],
              [2, 5],
              [3, 6]]


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


    def "Test validPos"() {

        given:
        Position pos = new Position(x, y)

        when:

        boolean valid = matrix.validPos(pos)

        then:

        if (x >= 0 && y >= 0 && x < width && y < height) valid else !valid


        where:
        x << [-16, 0, -1, 0, 1, 2, 23, 46, 7, 78, 15, 99]
        y << [-10, 8, 1, -2, 3, 6, 18, 0, 123, 34, 15, 8]

    }


}

