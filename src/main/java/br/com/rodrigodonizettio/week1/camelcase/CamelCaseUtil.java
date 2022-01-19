package br.com.rodrigodonizettio.week1.camelcase;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class CamelCaseUtil {
    private static final Logger log = LogManager.getLogger(CamelCaseUtil.class);

    public static List<String> convertToStringList(String input) {
        if(input.isBlank()) {
            throw new IllegalArgumentException("String is Blank!");
        } else if(Character.isDigit(input.charAt(0))) {
            throw new IllegalArgumentException("Invalid String → Must not start with numbers!");
        } else {
            Pattern patternSpecialChars = Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE);
            Matcher matcherSpecialChars = patternSpecialChars.matcher(input);
            boolean hasSpecialChar = matcherSpecialChars.find();
            if(hasSpecialChar) {
                throw new IllegalArgumentException("Invalid String → Special characters are not allowed. Use letters and numbers only.");
            } else {
                List<String> result = Arrays.asList(input.split("(?<!(^|[A-Z]))(?=[A-Z])|(?<!^)(?=[A-Z][a-z]|([0-9][0-9]))"));
                return performLowerCaseOnFirstCharacter(result);
            }
        }
    }

    private static List<String> performLowerCaseOnFirstCharacter(List<String> result) {
        List<String> tmpResult = result.stream()
                .map(word -> {
                    if(Character.isUpperCase(word.charAt(0)) && Character.isLowerCase(word.charAt(1))) {
                        word = word.toLowerCase();
                    }
                    return word;
                })
                .collect(Collectors.toList());
        return tmpResult;
    }
}
