package org.dida43.map.walker.map;

import org.dida43.map.walker.CodeChallengeException;
import org.dida43.map.walker.enums.LetterCharacters;
import org.dida43.map.walker.enums.NonPathCharacters;
import org.dida43.map.walker.enums.PathCharacters;
import org.dida43.map.walker.pojos.Position;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AsciiMapTest {

  @Test public void test_getCharForPosition() throws Exception {
    String mapAsString = new StringBuilder()
      .append(PathCharacters.VERTICAL.value())
      .append(LetterCharacters.A)
      .append(NonPathCharacters.SPACE.value())
      .toString();

    AsciiMap asciiMap = AsciiMap.ofString(mapAsString);

    assertEquals(PathCharacters.VERTICAL.value(), asciiMap.getCharForPosition(new Position(0, 0)));
    assertEquals(LetterCharacters.A.name().charAt(0),
                 asciiMap.getCharForPosition(new Position(0, 1)));
    assertEquals(NonPathCharacters.SPACE.value(), asciiMap.getCharForPosition(new Position(0, 2)));
    assertEquals(NonPathCharacters.OUT_OF_BOUNDS.value(),
                 asciiMap.getCharForPosition(new Position(1, 1)));
  }

  @Test public void test_visitPosition() throws Exception {
    String mapAsString = new StringBuilder().append(PathCharacters.VERTICAL.value()).toString();

    AsciiMap asciiMap = AsciiMap.ofString(mapAsString);
    Position positionToVisit = new Position(0, 0);

    asciiMap.visitPosition(positionToVisit);

    assertTrue(asciiMap.isPositionVisited(positionToVisit));

    Assertions.assertThrows(AsciiMap.VisitPositionException.class, () -> {
      asciiMap.visitPosition(new Position(1, 1));
    });
    Assertions.assertThrows(AsciiMap.IsPositionVisitedException.class, () -> {
      asciiMap.isPositionVisited(new Position(1, 1));
    });
  }

  @Test public void test_startPosition() throws Exception {
    String mapAsString = new StringBuilder()
      .append(LetterCharacters.A)
      .append(System.lineSeparator())
      .append(NonPathCharacters.SPACE.value())
      .append(NonPathCharacters.START.value())
      .append(System.lineSeparator())
      .append(LetterCharacters.A)
      .toString();

    AsciiMap asciiMap = AsciiMap.ofString(mapAsString);

    assertEquals(new Position(1, 1), asciiMap.startPosition());
  }

  @Test public void test_startPosition_NoStart() throws Exception {
    String mapAsString = new StringBuilder()
      .append(LetterCharacters.A)
      .append(System.lineSeparator())
      .append(NonPathCharacters.SPACE.value())
      .append(System.lineSeparator())
      .append(LetterCharacters.A)
      .toString();

    AsciiMap asciiMap = AsciiMap.ofString(mapAsString);

    Assertions.assertThrows(AsciiMap.NoStartException.class, asciiMap::startPosition);
  }

  @Test public void test_startPosition_MultipleStart() throws Exception {
    String mapAsString = new StringBuilder()
      .append(LetterCharacters.A)
      .append(System.lineSeparator())
      .append(NonPathCharacters.START.value())
      .append(NonPathCharacters.START.value())
      .append(System.lineSeparator())
      .append(LetterCharacters.A)
      .toString();

    AsciiMap asciiMap = AsciiMap.ofString(mapAsString);

    Assertions.assertThrows(AsciiMap.MultipleStartsException.class, asciiMap::startPosition);
  }

  @Test public void test_endPosition() throws Exception {
    String mapAsString = new StringBuilder()
      .append(LetterCharacters.A)
      .append(System.lineSeparator())
      .append(NonPathCharacters.SPACE.value())
      .append(NonPathCharacters.END.value())
      .append(System.lineSeparator())
      .append(LetterCharacters.A)
      .toString();

    AsciiMap asciiMap = AsciiMap.ofString(mapAsString);

    assertEquals(new Position(1, 1), asciiMap.endPosition());
  }

  @Test public void test_endPosition_NoEnd() throws Exception {
    String mapAsString = new StringBuilder()
      .append(LetterCharacters.A)
      .append(System.lineSeparator())
      .append(NonPathCharacters.SPACE.value())
      .append(System.lineSeparator())
      .append(LetterCharacters.A)
      .toString();

    AsciiMap asciiMap = AsciiMap.ofString(mapAsString);

    Assertions.assertThrows(AsciiMap.NoEndException.class, asciiMap::endPosition);
  }

  @Test public void test_endPosition_MultipleEnd() throws Exception {
    String mapAsString = new StringBuilder()
      .append(LetterCharacters.A)
      .append(System.lineSeparator())
      .append(NonPathCharacters.END.value())
      .append(NonPathCharacters.END.value())
      .append(System.lineSeparator())
      .append(LetterCharacters.A)
      .toString();

    AsciiMap asciiMap = AsciiMap.ofString(mapAsString);

    Assertions.assertThrows(AsciiMap.MultipleEndsException.class, asciiMap::endPosition);
  }

  @Test public void test_ofString() throws Exception {
    String mapAsString = new StringBuilder()
      .append(LetterCharacters.A)
      .append(System.lineSeparator())
      .append(NonPathCharacters.SPACE.value())
      .append(NonPathCharacters.START.value())
      .append(System.lineSeparator())
      .append(LetterCharacters.A)
      .toString();

    AsciiMap asciiMap = AsciiMap.ofString(mapAsString);
    assertNotNull(asciiMap);
  }

  @Test public void test_ofString_Negative() {

    Assertions.assertThrows(CodeChallengeException.class, () -> {
      AsciiMap.ofString(null);
    });
    Assertions.assertThrows(CodeChallengeException.class, () -> {
      AsciiMap.ofString("");
    });
    Assertions.assertThrows(CodeChallengeException.class, () -> {
      AsciiMap.ofString("¢");
    });
  }
}
