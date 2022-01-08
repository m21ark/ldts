import spock.lang.Specification

class Test_ArenaModel extends Specification {
    def matrix
    def bird
    def arenaModel


    def setup() throws Exception {
        matrix = Mock(Matrix)
        bird = Mock(Bird)
        arenaModel = new ArenaModel(20, 25, matrix, "#00FF00")
        arenaModel.setBird(bird)
        arenaModel.setMatrix(matrix)
    }


    def "Test getPlayerScore"() {
        given:
        bird.getCoinCount() >> 5

        when:
        def score = arenaModel.getPlayerScore()

        then:
        score == 5
    }

    def "Test getPlayerHp"() {
        given:
        bird.getHp() >> 5

        when:
        def hp = arenaModel.getPlayerHp()

        then:
        hp == 5
    }


}


