package org.dida43.map.walker.characters;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PathCharactersTest {

  @Test public void test_charset_ContainsAllCharacters() {
    assertEquals("+-|", PathCharacters.charset);
  }

  @Test public void test_isPathCharacter_AllCharsPathCharTrueNonCharFalse() {
    for (char c : PathCharacters.charset.toCharArray()) {
      assertTrue(PathCharacters.isPathCharacter(c));
    }

    assertFalse(LetterCharacters.isLetter('X'));
  }
}
