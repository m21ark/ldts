import spock.lang.Specification

class Test_ArenaModel extends Specification {
    def matrix
    def bird
    def arenaModel


    def setup() throws Exception {
        matrix = Mock(Matrix)
        bird = Mock(Bird)
        arenaModel = new ArenaModel(new Dimensions(20, 25), matrix, "#00FF00")
        arenaModel.setBird(bird)
        arenaModel.setMatrix(matrix)
    }


    def "Test getPlayerScore"(int expectedScore) {
        given:
        bird.getCoinCount() >> expectedScore

        when:
        def score = arenaModel.getPlayerScore()

        then:
        score == expectedScore

        where:
        expectedScore << [-10, 0, -1, 0, 1, 2, 4, 46, 7, 78, 15, 9999]

    }

    def "Test getPlayerHp"(int expectedHp) {
        given:
        bird.getHp() >> expectedHp

        when:
        def hp = arenaModel.getPlayerHp()

        then:
        hp == expectedHp

        where:
        expectedHp << [-10, 0, -1, 0, 1, 2, 4, 46, 7, 78, 15, 9999]
    }


}


