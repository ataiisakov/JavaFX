import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MainTest {
    @DisplayName("test1")
    @Test
    void test1() {
        Assertions.assertTrue(true);
    }
    @DisplayName("test2")
    @Test
    void test2() {
        Assertions.assertTrue(true);
    }
    @DisplayName("test3")
    @Test
    void test3() {
        Assertions.assertTrue(true);

    }
    @DisplayName("test4")
    @Test
    void test4() {
        Assertions.assertTrue(true);

    }
    @Test
    @DisplayName("test5")
    void test5() {
        Assertions.assertTrue(true);

    }
}