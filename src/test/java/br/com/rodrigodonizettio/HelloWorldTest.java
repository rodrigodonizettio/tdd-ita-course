package br.com.rodrigodonizettio;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verifyNoMoreInteractions;

@ExtendWith(MockitoExtension.class)
public class HelloWorldTest {

  @Mock
  HelloWorld helloWorld;

  @Test
  void ensureApplicationIsWorkingTest() {
    String[] args = new String[0];
    helloWorld.main(args);

    assertEquals("Hello, World!", helloWorld.getResult());
    verifyNoMoreInteractions(helloWorld);
  }
}
