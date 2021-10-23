package org.dida43.map.walker.enums;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PathCharactersTest {

  @Test public void testCharset() {
    assertEquals("+-|x", PathCharacters.charset);
  }

  @Test public void testIsPathCharacter() {
    for (char c : PathCharacters.charset.toCharArray()) {
      assertTrue(PathCharacters.isPathCharacter(c));
    }

    assertFalse(LetterCharacters.isLetter('X'));
  }
}
