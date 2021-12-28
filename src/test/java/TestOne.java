import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class TestOne {
    private List<Integer> list;

    @BeforeEach
    public void listCreator() {
        this.list = Arrays.asList(1, 2, 4, 2, 5);
    }

    @Test
    public void sum() {
        // ...
        Assertions.assertEquals(1, 1);
    }







}