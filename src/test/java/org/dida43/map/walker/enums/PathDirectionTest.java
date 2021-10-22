package org.dida43.map.walker.enums;

import org.dida43.map.walker.exceptions.AsciiMapException;
import org.dida43.map.walker.map.AsciiMap;
import org.dida43.map.walker.pojos.Position;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PathDirectionTest {

  @Test public void startingDirectionUp() throws AsciiMapException {
    String asciiMapString = new StringBuilder()
      .append(PathCharacters.VERTICAL.value())
      .append(System.lineSeparator())
      .append(PathCharacters.START.value())
      .toString();

    AsciiMap map = AsciiMap.ofString(asciiMapString);
    Position position = new Position(1, 0);

    assertEquals(PathDirection.UP, PathDirection.getStartingPathDirection(map, position));
  }

  @Test public void startingDirectionDown() throws AsciiMapException {
    String asciiMapString = new StringBuilder()
      .append(PathCharacters.START.value())
      .append(System.lineSeparator())
      .append(PathCharacters.VERTICAL.value())
      .toString();

    AsciiMap map = AsciiMap.ofString(asciiMapString);
    Position position = new Position(0, 0);

    assertEquals(PathDirection.DOWN, PathDirection.getStartingPathDirection(map, position));
  }

  @Test public void startingDirectionLeft() throws AsciiMapException {
    String asciiMapString = new StringBuilder()
      .append(PathCharacters.HORIZONTAL.value())
      .append(PathCharacters.START.value())
      .toString();

    AsciiMap map = AsciiMap.ofString(asciiMapString);
    Position position = new Position(0, 1);

    assertEquals(PathDirection.LEFT, PathDirection.getStartingPathDirection(map, position));
  }

  @Test public void startingDirectionRight() throws AsciiMapException {
    String asciiMapString = new StringBuilder()
      .append(PathCharacters.START.value())
      .append(PathCharacters.HORIZONTAL.value())
      .toString();

    AsciiMap map = AsciiMap.ofString(asciiMapString);
    Position position = new Position(0, 0);

    assertEquals(PathDirection.RIGHT, PathDirection.getStartingPathDirection(map, position));
  }
}
