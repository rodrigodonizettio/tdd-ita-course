package br.com.rodrigodonizettio.week1.stack;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.EmptyStackException;

import static org.junit.jupiter.api.Assertions.*;

public class MyStackTest {
    private static final Logger log = LogManager.getLogger(MyStackTest.class);
    private static final Integer SIZE = 3;

    @Mock
    MyStack myStack = new MyStack(SIZE);

    @BeforeAll
    static void beforeAllTest() {
        log.info("@BeforeAllTest");
        assertTrue(SIZE > 0);
    }

    @BeforeEach
    void beforeEachTest() {
        log.info("@BeforeEach");
        myStack.popAll();
    }

    @Test
    void assertIsEmptyTest() {
        assertTrue(myStack.isEmpty());
        assertEquals(0, myStack.size());
    }

    @Test
    void assertHasOneElementTest() {
        myStack.push(1);

        assertEquals(1, myStack.top());
        assertFalse(myStack.isEmpty());
        assertEquals(1, myStack.size());
    }

    @Test
    void assertCanPopElementsTest() {
        myStack.push(1);
        myStack.push(2);

        assertEquals(2, myStack.size());

        assertEquals(2, myStack.pop());
        assertEquals(1, myStack.size());
    }

    @Test
    void assertThrowsExceptionWhenPoppingEmptyStackTest() {
        assertThrows(EmptyStackException.class, () -> myStack.pop());
    }

    @Test
    void assertThrowsExceptionWhenPushingFullStackTest() {
        assertDoesNotThrow(() -> myStack.push(1));
        assertDoesNotThrow(() -> myStack.push(2));
        assertDoesNotThrow(() -> myStack.push(3));
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> myStack.push(4));
    }
}
