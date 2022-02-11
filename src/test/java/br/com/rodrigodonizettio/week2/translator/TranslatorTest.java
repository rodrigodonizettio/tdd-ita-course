package br.com.rodrigodonizettio.week2.translator;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TranslatorTest {
    @Mock
    Translator translator = new Translator();

    @Test
    void shouldReturnNullWhenWordNotFoundInTranslatorTest() {
        String word = "handsome";

        NullPointerException nullPointerException = assertThrows(NullPointerException.class, () -> translator.translateWord(word));
        assertEquals(String.format("Word '%s' wasn't found in translator!", word), nullPointerException.getMessage());
    }

    @Test
    void shouldTranslateWordsTest() {
        String word1 = "good";
        String word2 = "bad";
        String word3 = "ugly";

        assertDoesNotThrow(() -> {
            String result = translator.translateWord(word1);
            assertEquals("bom", result);

            result = translator.translateWord(word2);
            assertEquals("mau", result);

            result = translator.translateWord(word3);
            assertEquals("feio", result);
        });
    }

    @Test
    void shouldTranslateSentenceTest() {
        String sentence = "The greed may kill";

        String result = translator.translateSentence(sentence);
        assertEquals("a ganância pode matar", result);

    }
}