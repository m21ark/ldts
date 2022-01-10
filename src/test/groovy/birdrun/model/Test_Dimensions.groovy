package birdrun.model

import birdrun.model.Dimensions
import spock.lang.Specification

class Test_Dimensions extends Specification {

    def "Test get / set"(int width, int height) {

        given:
        int newWidth = 20
        int newHeight = 30
        Dimensions dimensions = new Dimensions(width, height)

        when:
        int width0 = dimensions.getWidth()
        int height0 = dimensions.getHeight()

        dimensions.setWidth(newWidth)
        dimensions.setHeight(newHeight)

        then:
        width == width0
        height == height0

        dimensions.getWidth() == newWidth
        dimensions.getHeight() == newHeight


        where:
        width | height
        1     | 15
        2     | 1
        10    | 1
        20    | 35


    }


}