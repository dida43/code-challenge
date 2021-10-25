package org.dida43.map.walker.map;

import org.dida43.map.walker.CodeChallengeException;
import org.dida43.map.walker.characters.Characters;
import org.dida43.map.walker.path.Position;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AsciiMapTest {

  @Test public void test_getCharForPosition() throws Exception {
    String mapAsString = new StringBuilder()
      .append(Characters.VERTICAL.value())
      .append(Characters.A.value())
      .append(Characters.SPACE.value())
      .toString();

    AsciiMap asciiMap = AsciiMap.ofString(mapAsString);

    assertEquals(Characters.VERTICAL.value(), asciiMap.getCharForPosition(new Position(0, 0)));
    assertEquals(Characters.A.value(), asciiMap.getCharForPosition(new Position(0, 1)));
    assertEquals(Characters.SPACE.value(), asciiMap.getCharForPosition(new Position(0, 2)));
    assertEquals(Characters.NON_VALID.value(), asciiMap.getCharForPosition(new Position(1, 1)));
  }

  @Test public void test_visitPosition() throws Exception {
    String mapAsString = new StringBuilder().append(Characters.VERTICAL.value()).toString();

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
      .append(Characters.A.value())
      .append(System.lineSeparator())
      .append(Characters.SPACE.value())
      .append(Characters.START.value())
      .append(System.lineSeparator())
      .append(Characters.A.value())
      .toString();

    AsciiMap asciiMap = AsciiMap.ofString(mapAsString);

    assertEquals(new Position(1, 1), asciiMap.startPosition());
  }

  @Test public void test_startPosition_NoStart() throws Exception {
    String mapAsString = new StringBuilder()
      .append(Characters.A.value())
      .append(System.lineSeparator())
      .append(Characters.SPACE.value())
      .append(System.lineSeparator())
      .append(Characters.A.value())
      .toString();

    AsciiMap asciiMap = AsciiMap.ofString(mapAsString);

    Assertions.assertThrows(AsciiMap.NoStartException.class, asciiMap::startPosition);
  }

  @Test public void test_startPosition_MultipleStart() throws Exception {
    String mapAsString = new StringBuilder()
      .append(Characters.A.value())
      .append(System.lineSeparator())
      .append(Characters.START.value())
      .append(Characters.START.value())
      .append(System.lineSeparator())
      .append(Characters.A.value())
      .toString();

    AsciiMap asciiMap = AsciiMap.ofString(mapAsString);

    Assertions.assertThrows(AsciiMap.MultipleStartsException.class, asciiMap::startPosition);
  }

  @Test public void test_endPosition() throws Exception {
    String mapAsString = new StringBuilder()
      .append(Characters.A.value())
      .append(System.lineSeparator())
      .append(Characters.SPACE.value())
      .append(Characters.END.value())
      .append(System.lineSeparator())
      .append(Characters.A.value())
      .toString();

    AsciiMap asciiMap = AsciiMap.ofString(mapAsString);

    assertEquals(new Position(1, 1), asciiMap.endPosition());
  }

  @Test public void test_endPosition_NoEnd() throws Exception {
    String mapAsString = new StringBuilder()
      .append(Characters.A.value())
      .append(System.lineSeparator())
      .append(Characters.SPACE.value())
      .append(System.lineSeparator())
      .append(Characters.A.value())
      .toString();

    AsciiMap asciiMap = AsciiMap.ofString(mapAsString);

    Assertions.assertThrows(AsciiMap.NoEndException.class, asciiMap::endPosition);
  }

  @Test public void test_endPosition_MultipleEnd() throws Exception {
    String mapAsString = new StringBuilder()
      .append(Characters.A.value())
      .append(System.lineSeparator())
      .append(Characters.END.value())
      .append(Characters.END.value())
      .append(System.lineSeparator())
      .append(Characters.A.value())
      .toString();

    AsciiMap asciiMap = AsciiMap.ofString(mapAsString);

    Assertions.assertThrows(AsciiMap.MultipleEndsException.class, asciiMap::endPosition);
  }

  @Test public void test_ofString() throws Exception {
    String mapAsString = new StringBuilder()
      .append(Characters.A.value())
      .append(System.lineSeparator())
      .append(Characters.SPACE.value())
      .append(Characters.START.value())
      .append(System.lineSeparator())
      .append(Characters.A.value())
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
