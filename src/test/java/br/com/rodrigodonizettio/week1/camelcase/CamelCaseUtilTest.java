package br.com.rodrigodonizettio.week1.camelcase;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
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

    @Test
    void assertcamelCaseStringWasCorrectlyConvertedToStringListTest() {
        String input1 = "nome";
        List<String> result1 = List.of("nome");

        String input2 = "Nome";
        List<String> result2 = List.of("nome");

        String input3 = "nomeComposto";
        List<String> result3 = List.of("nome", "composto");

        String input4 = "NomeComposto";
        List<String> result4 = List.of("nome", "composto");

        String input5 = "CPF";
        List<String> result5 = List.of("CPF");

        String input6 = "numeroCPF";
        List<String> result6 = List.of("numero", "CPF");

        String input7 = "numeroCPFContribuinte";
        List<String> result7 = List.of("numero", "CPF", "contribuinte");

        String input8 = "recupera10Primeiros";
        List<String> result8 = List.of("recupera", "10", "primeiros");

        String input9 = "10Primeiros";
        IllegalArgumentException illegalArgumentException1 = assertThrows(IllegalArgumentException.class, () -> CamelCaseUtil.convertToStringList(input9));
        assertEquals("Invalid String → Must not start with numbers!", illegalArgumentException1.getMessage());

        String input10 = "nome#Composto";
        IllegalArgumentException illegalArgumentException2 = assertThrows(IllegalArgumentException.class, () -> CamelCaseUtil.convertToStringList(input10));
        assertEquals("Invalid String → Special characters are not allowed. Use letters and numbers only.", illegalArgumentException2.getMessage());

        assertAll("Should pass all test cases to ensure CamelCaseUtil.convertToStringList is properly working",
                () -> assertEquals(result1, CamelCaseUtil.convertToStringList(input1)),
                () -> assertEquals(result2, CamelCaseUtil.convertToStringList(input2)),
                () -> assertEquals(result3, CamelCaseUtil.convertToStringList(input3)),
                () -> assertEquals(result4, CamelCaseUtil.convertToStringList(input4)),
                () -> assertEquals(result5, CamelCaseUtil.convertToStringList(input5)),
                () -> assertEquals(result6, CamelCaseUtil.convertToStringList(input6)),
                () -> assertEquals(result7, CamelCaseUtil.convertToStringList(input7)),
                () -> assertEquals(result8, CamelCaseUtil.convertToStringList(input8)));
    }
}
