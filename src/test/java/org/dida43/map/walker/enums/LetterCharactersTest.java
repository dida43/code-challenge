package org.dida43.map.walker.enums;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LetterCharactersTest {

  @Test public void testCharset() {
    assertEquals("ABCDEGHILNOS", LetterCharacters.charset);
  }

  @Test public void testIsLetterCharacter() {
    for (char c : LetterCharacters.charset.toCharArray()) {
      assertTrue(LetterCharacters.isLetter(c));
    }

    assertFalse(LetterCharacters.isLetter('X'));
  }
}
