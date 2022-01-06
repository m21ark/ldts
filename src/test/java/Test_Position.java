import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Test_Position {
    Position position = new Position(2, 4);
    Position position2 = new Position(4, 5);

    @Test
    public void Test_Get_Positions() {
        Assertions.assertEquals(2, position.getX());
        Assertions.assertEquals(4, position.getY());
    }

    @Test
    public void Test_Equal_Positions() {

        Assertions.assertNotEquals(position, position2);
        position2.set(new Position(2, 4));
        Assertions.assertEquals(position, position2);
    }

    @Test
    public void Test_Get_Set() {

        position.setX(3);
        position2.setY(10);
        Assertions.assertEquals(position.getX(), 3);
        Assertions.assertEquals(position2.getY(), 10);

    }

}
