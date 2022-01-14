package br.com.rodrigodonizettio.week1.camelcase;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CamelCaseUtilTest {
    @Test
    void assertNullStringWillThrowNullPointerExceptionTest() {
        String input = null;
        assertThrows(NullPointerException.class, () -> CamelCaseUtil.convertToStringList(input));
    }

    @Test
    void assertEmptyStringWillThrowIllegalArgumentExceptionTest() {
        String input = "";
        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, () -> CamelCaseUtil.convertToStringList(input));
        assertEquals("String is Blank!", illegalArgumentException.getMessage());
    }
}
