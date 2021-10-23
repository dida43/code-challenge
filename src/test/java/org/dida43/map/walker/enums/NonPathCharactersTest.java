package org.dida43.map.walker.enums;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class NonPathCharactersTest {

  @Test public void testAllValues() {

    assertEquals('@', NonPathCharacters.START.value());
    assertEquals(' ', NonPathCharacters.SPACE.value());
    assertEquals('\u0000', NonPathCharacters.OUT_OF_BOUNDS.value());
  }
}
