package org.dida43.map.walker.characters;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LetterCharactersTest {

  @Test public void test_charset_ContainsAllCharacters() {
    assertEquals("ABCDEGHILNOS", LetterCharacters.charset);
  }

  @Test public void test_isLetter_AllCharsLetterTrueNonCharFalse() {
    for (char c : LetterCharacters.charset.toCharArray()) {
      assertTrue(LetterCharacters.isLetter(c));
    }

    assertFalse(LetterCharacters.isLetter('X'));
  }
}
