package br.com.rodrigodonizettio.week2.translator;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Translator {
    private static final Logger log = LogManager.getLogger(Translator.class);

    private final ObjectMapper objectMapper = new ObjectMapper();
    private Map<String, String> translatorMap = new HashMap();

    public Translator() {
        try {
            translatorMap = objectMapper.readValue(Paths.get("src/main/resources/translator.json").toFile(), Map.class);
        } catch (IOException e) {
            log.error(String.format("Message: %s with Cause: %s", e.getMessage(), e.getCause()));
        }
    }

    public String translateWord(String word) {
        String translatedWord = translatorMap.get(word);
        validateTranslation(word, translatedWord);
        return translatedWord;
    }

    public String translateSentence(String sentence) {
        List<String> splittedSentence = List.of(sentence.toLowerCase().trim().split(" "));
        StringBuilder translatedSentence = new StringBuilder();

        splittedSentence.forEach(word -> {
            String translatedWord = translatorMap.get(word);
            validateTranslation(word, translatedWord);
            translatedSentence.append(translatedWord + " ");
        });
        return translatedSentence.toString().trim();
    }

    private void validateTranslation(String word, String translatedWord) {
        if(null == translatedWord) {
            throw new NullPointerException(String.format("Word '%s' wasn't found in translator!", word));
        }
    }
}
