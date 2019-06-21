import org.junit.Test;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;

/** An Integer tester created by Flik Enterprises. */
public class Flik {
    public static boolean isSameNumber(Integer a, Integer b) {
        return a == b;
    }

    @Test
    public void isSameNumberTest() {
        int a = 5;
        int b = 5;
        assertTrue(isSameNumber(a, b));
        assertFalse(isSameNumber(a, 6));
        assertTrue(isSameNumber(127, 127));
    }
}
