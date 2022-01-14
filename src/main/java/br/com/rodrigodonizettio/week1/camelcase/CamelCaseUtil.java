package br.com.rodrigodonizettio.week1.camelcase;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CamelCaseUtil {
    private static final Logger log = LogManager.getLogger(CamelCaseUtil.class);

    public static void convertToStringList(String input) {
        if(!input.isBlank()) {
            log.info("Not null!");
        } else {
            log.info("Null!");
        }
    }
}
