package br.com.rodrigodonizettio;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class HelloWorld {
    private static Logger log = LogManager.getLogger(HelloWorld.class);

    private static String result;

    public static void main(String[] args) {
        result = "Hello, World!";
        log.info(getResult());
    }

    public static String getResult() {
        return result;
    }
}
