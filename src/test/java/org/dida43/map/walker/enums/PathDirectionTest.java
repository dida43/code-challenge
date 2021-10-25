package org.dida43.map.walker.enums;

import org.dida43.map.walker.CodeChallengeException;
import org.dida43.map.walker.map.AsciiMap;
import org.dida43.map.walker.pojos.Position;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PathDirectionTest {

  @Test public void test_getStartingPathDirection_Up() throws CodeChallengeException {
    String asciiMapString = new StringBuilder()
      .append(PathCharacters.VERTICAL.value())
      .append(System.lineSeparator())
      .append(NonPathCharacters.START.value())
      .toString();

    AsciiMap map = AsciiMap.ofString(asciiMapString);
    Position position = new Position(1, 0);

    assertEquals(PathDirection.UP, PathDirection.getStartingPathDirection(map, position));
  }

  @Test public void test_getStartingPathDirection_Down() throws CodeChallengeException {
    String asciiMapString = new StringBuilder()
      .append(NonPathCharacters.START.value())
      .append(System.lineSeparator())
      .append(PathCharacters.VERTICAL.value())
      .toString();

    AsciiMap map = AsciiMap.ofString(asciiMapString);
    Position position = new Position(0, 0);

    assertEquals(PathDirection.DOWN, PathDirection.getStartingPathDirection(map, position));
  }

  @Test public void test_getStartingPathDirection_Left() throws CodeChallengeException {
    String asciiMapString = new StringBuilder()
      .append(PathCharacters.HORIZONTAL.value())
      .append(NonPathCharacters.START.value())
      .toString();

    AsciiMap map = AsciiMap.ofString(asciiMapString);
    Position position = new Position(0, 1);

    assertEquals(PathDirection.LEFT, PathDirection.getStartingPathDirection(map, position));
  }

  @Test public void test_getStartingPathDirection_Right() throws CodeChallengeException {
    String asciiMapString = new StringBuilder()
      .append(NonPathCharacters.START.value())
      .append(PathCharacters.HORIZONTAL.value())
      .toString();

    AsciiMap map = AsciiMap.ofString(asciiMapString);
    Position position = new Position(0, 0);

    assertEquals(PathDirection.RIGHT, PathDirection.getStartingPathDirection(map, position));
  }

  @Test public void test_getStartingPathDirection_Multiple() throws CodeChallengeException {
    String asciiMapString = new StringBuilder()
      .append(NonPathCharacters.START.value())
      .append(PathCharacters.HORIZONTAL.value())
      .append(System.lineSeparator())
      .append(PathCharacters.VERTICAL.value())
      .toString();

    AsciiMap map = AsciiMap.ofString(asciiMapString);
    Position position = new Position(0, 0);

    Assertions.assertThrows(PathDirection.MultipleStartException.class, () -> {
      PathDirection.getStartingPathDirection(map, position);
    });
  }

  @Test public void test_getStartingPathDirection_None() throws CodeChallengeException {
    String asciiMapString = new StringBuilder().append(NonPathCharacters.START.value()).toString();

    AsciiMap map = AsciiMap.ofString(asciiMapString);
    Position position = new Position(0, 0);

    Assertions.assertThrows(PathDirection.NoStartException.class, () -> {
      PathDirection.getStartingPathDirection(map, position);
    });
  }

  @Test public void test_getStartingPathDirection_IgnoreNonPathChars() throws CodeChallengeException
  {
    String asciiMapString = new StringBuilder()
      .append(NonPathCharacters.SPACE.value())
      .append(NonPathCharacters.START.value())
      .append(PathCharacters.HORIZONTAL.value())
      .append(System.lineSeparator())
      .append(NonPathCharacters.SPACE.value())
      .append(NonPathCharacters.START.value())
      .toString();

    AsciiMap map = AsciiMap.ofString(asciiMapString);
    Position position = new Position(0, 1);

    assertEquals(PathDirection.RIGHT, PathDirection.getStartingPathDirection(map, position));
  }

  @Test public void test_getPathDirection_Horizontal() throws CodeChallengeException {
    String asciiMapString = new StringBuilder()
      .append(PathCharacters.HORIZONTAL.value())
      .append(LetterCharacters.A)
      .append(PathCharacters.HORIZONTAL.value())
      .toString();

    AsciiMap map = AsciiMap.ofString(asciiMapString);
    Position position = new Position(0, 1);

    PathDirection currentDirection = PathDirection.RIGHT;
    assertEquals(currentDirection, PathDirection.getPathDirection(map, position, currentDirection));

    currentDirection = PathDirection.LEFT;
    assertEquals(currentDirection, PathDirection.getPathDirection(map, position, currentDirection));
  }

  @Test public void test_getPathDirection_Vertical() throws CodeChallengeException {
    String asciiMapString = new StringBuilder()
      .append(PathCharacters.VERTICAL.value())
      .append(System.lineSeparator())
      .append(LetterCharacters.A)
      .append(System.lineSeparator())
      .append(PathCharacters.VERTICAL.value())
      .toString();

    AsciiMap map = AsciiMap.ofString(asciiMapString);
    Position position = new Position(1, 0);

    PathDirection currentDirection = PathDirection.UP;
    assertEquals(currentDirection, PathDirection.getPathDirection(map, position, currentDirection));

    currentDirection = PathDirection.DOWN;
    assertEquals(currentDirection, PathDirection.getPathDirection(map, position, currentDirection));
  }

  @Test public void test_getPathDirection_StraightThroughIntersectionLeftRight()
    throws CodeChallengeException
  {
    String asciiMapString = new StringBuilder()
      .append(PathCharacters.HORIZONTAL.value())
      .append(LetterCharacters.A)
      .append(PathCharacters.VERTICAL.value())
      .toString();

    AsciiMap map = AsciiMap.ofString(asciiMapString);
    Position position = new Position(0, 1);

    PathDirection currentDirection = PathDirection.RIGHT;
    assertEquals(currentDirection, PathDirection.getPathDirection(map, position, currentDirection));
  }

  @Test public void test_getPathDirection_StraightThroughIntersectionRightLeft()
    throws CodeChallengeException
  {
    String asciiMapString = new StringBuilder()
      .append(PathCharacters.VERTICAL.value())
      .append(LetterCharacters.A)
      .append(PathCharacters.HORIZONTAL.value())
      .toString();

    AsciiMap map = AsciiMap.ofString(asciiMapString);
    Position position = new Position(0, 1);

    PathDirection currentDirection = PathDirection.LEFT;
    assertEquals(currentDirection, PathDirection.getPathDirection(map, position, currentDirection));
  }

  @Test public void test_getPathDirection_StraightThroughIntersectionUpDown()
    throws CodeChallengeException
  {
    String asciiMapString = new StringBuilder()
      .append(PathCharacters.VERTICAL.value())
      .append(System.lineSeparator())
      .append(LetterCharacters.A)
      .append(System.lineSeparator())
      .append(PathCharacters.HORIZONTAL.value())
      .toString();

    AsciiMap map = AsciiMap.ofString(asciiMapString);
    Position position = new Position(1, 0);

    PathDirection currentDirection = PathDirection.DOWN;
    assertEquals(currentDirection, PathDirection.getPathDirection(map, position, currentDirection));
  }

  @Test public void test_getPathDirection_StraightThroughIntersectionDownUp()
    throws CodeChallengeException
  {
    String asciiMapString = new StringBuilder()
      .append(PathCharacters.HORIZONTAL.value())
      .append(System.lineSeparator())
      .append(LetterCharacters.A)
      .append(System.lineSeparator())
      .append(PathCharacters.VERTICAL.value())
      .toString();

    AsciiMap map = AsciiMap.ofString(asciiMapString);
    Position position = new Position(1, 0);

    PathDirection currentDirection = PathDirection.UP;
    assertEquals(currentDirection, PathDirection.getPathDirection(map, position, currentDirection));
  }

  @Test public void test_getPathDirection_TurnUp() throws CodeChallengeException {
    String asciiMapString = new StringBuilder()
      .append(PathCharacters.VERTICAL.value())
      .append(System.lineSeparator())
      .append(PathCharacters.TURN.value())
      .toString();

    AsciiMap map = AsciiMap.ofString(asciiMapString);
    Position position = new Position(1, 0);

    assertEquals(PathDirection.UP,
                 PathDirection.getPathDirection(map, position, PathDirection.LEFT));

    assertEquals(PathDirection.UP,
                 PathDirection.getPathDirection(map, position, PathDirection.RIGHT));
  }

  @Test public void test_getPathDirection_TurnDown() throws CodeChallengeException {
    String asciiMapString = new StringBuilder()
      .append(PathCharacters.TURN.value())
      .append(System.lineSeparator())
      .append(PathCharacters.VERTICAL.value())
      .toString();

    AsciiMap map = AsciiMap.ofString(asciiMapString);
    Position position = new Position(0, 0);

    assertEquals(PathDirection.DOWN,
                 PathDirection.getPathDirection(map, position, PathDirection.LEFT));

    assertEquals(PathDirection.DOWN,
                 PathDirection.getPathDirection(map, position, PathDirection.RIGHT));
  }

  @Test public void test_getPathDirection_TurnLeft() throws CodeChallengeException {
    String asciiMapString = new StringBuilder()
      .append(PathCharacters.HORIZONTAL.value())
      .append(PathCharacters.TURN.value())
      .toString();

    AsciiMap map = AsciiMap.ofString(asciiMapString);
    Position position = new Position(0, 1);

    assertEquals(PathDirection.LEFT,
                 PathDirection.getPathDirection(map, position, PathDirection.UP));

    assertEquals(PathDirection.LEFT,
                 PathDirection.getPathDirection(map, position, PathDirection.DOWN));
  }

  @Test public void test_getPathDirection_TurnRight() throws CodeChallengeException {
    String asciiMapString = new StringBuilder()
      .append(PathCharacters.TURN.value())
      .append(PathCharacters.HORIZONTAL.value())
      .toString();

    AsciiMap map = AsciiMap.ofString(asciiMapString);
    Position position = new Position(0, 0);

    assertEquals(PathDirection.RIGHT,
                 PathDirection.getPathDirection(map, position, PathDirection.UP));

    assertEquals(PathDirection.RIGHT,
                 PathDirection.getPathDirection(map, position, PathDirection.DOWN));
  }

  @Test public void test_getPathDirection_FakeTurnHorizontal() throws CodeChallengeException {
    String asciiMapString = new StringBuilder()
      .append(PathCharacters.HORIZONTAL.value())
      .append(PathCharacters.TURN.value())
      .append(PathCharacters.HORIZONTAL.value())
      .toString();

    AsciiMap map = AsciiMap.ofString(asciiMapString);
    Position position = new Position(0, 1);

    Assertions.assertThrows(PathDirection.FakeTurnException.class, () -> {
      PathDirection.getPathDirection(map, position, PathDirection.LEFT);
    });

    Assertions.assertThrows(PathDirection.FakeTurnException.class, () -> {
      PathDirection.getPathDirection(map, position, PathDirection.RIGHT);
    });
  }

  @Test public void test_getPathDirection_FakeTurnVertical() throws CodeChallengeException {
    String asciiMapString = new StringBuilder()
      .append(PathCharacters.VERTICAL.value())
      .append(System.lineSeparator())
      .append(PathCharacters.TURN.value())
      .append(System.lineSeparator())
      .append(PathCharacters.VERTICAL.value())
      .toString();

    AsciiMap map = AsciiMap.ofString(asciiMapString);
    Position position = new Position(1, 0);

    Assertions.assertThrows(PathDirection.FakeTurnException.class, () -> {
      PathDirection.getPathDirection(map, position, PathDirection.UP);
    });

    Assertions.assertThrows(PathDirection.FakeTurnException.class, () -> {
      PathDirection.getPathDirection(map, position, PathDirection.DOWN);
    });
  }

  @Test public void test_getPathDirection_TForkHorizontal() throws CodeChallengeException {
    String asciiMapString = new StringBuilder()
      .append(PathCharacters.VERTICAL.value())
      .append(System.lineSeparator())
      .append(PathCharacters.TURN.value())
      .append(System.lineSeparator())
      .append(PathCharacters.VERTICAL.value())
      .toString();

    AsciiMap map = AsciiMap.ofString(asciiMapString);
    Position position = new Position(1, 0);

    Assertions.assertThrows(PathDirection.TForkException.class, () -> {
      PathDirection.getPathDirection(map, position, PathDirection.LEFT);
    });

    Assertions.assertThrows(PathDirection.TForkException.class, () -> {
      PathDirection.getPathDirection(map, position, PathDirection.RIGHT);
    });
  }

  @Test public void test_getPathDirection_TForkVertical() throws CodeChallengeException {
    String asciiMapString = new StringBuilder()
      .append(PathCharacters.HORIZONTAL.value())
      .append(PathCharacters.TURN.value())
      .append(PathCharacters.HORIZONTAL.value())
      .toString();

    AsciiMap map = AsciiMap.ofString(asciiMapString);
    Position position = new Position(0, 1);

    Assertions.assertThrows(PathDirection.TForkException.class, () -> {
      PathDirection.getPathDirection(map, position, PathDirection.UP);
    });

    Assertions.assertThrows(PathDirection.TForkException.class, () -> {
      PathDirection.getPathDirection(map, position, PathDirection.DOWN);
    });
  }

  @Test public void test_getPathDirection_StraightThroughLetterLeftRight()
    throws CodeChallengeException
  {
    String asciiMapString = new StringBuilder()
      .append(PathCharacters.HORIZONTAL.value())
      .append(LetterCharacters.A)
      .append(LetterCharacters.A)
      .toString();

    AsciiMap map = AsciiMap.ofString(asciiMapString);
    Position position = new Position(0, 1);

    PathDirection currentDirection = PathDirection.RIGHT;
    assertEquals(currentDirection, PathDirection.getPathDirection(map, position, currentDirection));
  }

  @Test public void test_getPathDirection_StraightThroughLetterRightLeft()
    throws CodeChallengeException
  {
    String asciiMapString = new StringBuilder()
      .append(LetterCharacters.A)
      .append(LetterCharacters.A).append(PathCharacters.HORIZONTAL.value()).toString();

    AsciiMap map = AsciiMap.ofString(asciiMapString);
    Position position = new Position(0, 1);

    PathDirection currentDirection = PathDirection.LEFT;
    assertEquals(currentDirection, PathDirection.getPathDirection(map, position, currentDirection));
  }

  @Test public void test_getPathDirection_StraightThroughLetterUpDown()
    throws CodeChallengeException
  {
    String asciiMapString = new StringBuilder()
      .append(PathCharacters.VERTICAL.value())
      .append(System.lineSeparator())
      .append(LetterCharacters.A)
      .append(System.lineSeparator())
      .append(LetterCharacters.A)
      .toString();

    AsciiMap map = AsciiMap.ofString(asciiMapString);
    Position position = new Position(1, 0);

    PathDirection currentDirection = PathDirection.DOWN;
    assertEquals(currentDirection, PathDirection.getPathDirection(map, position, currentDirection));
  }

  @Test public void test_getPathDirection_StraightThroughLetterDownUp()
    throws CodeChallengeException
  {
    String asciiMapString = new StringBuilder()
      .append(LetterCharacters.A)
      .append(System.lineSeparator())
      .append(LetterCharacters.A)
      .append(System.lineSeparator())
      .append(PathCharacters.VERTICAL.value())
      .toString();

    AsciiMap map = AsciiMap.ofString(asciiMapString);
    Position position = new Position(1, 0);

    PathDirection currentDirection = PathDirection.UP;
    assertEquals(currentDirection, PathDirection.getPathDirection(map, position, currentDirection));
  }

  @Test public void test_getPathDirection_LetterTurnUp() throws CodeChallengeException {
    String asciiMapString = new StringBuilder()
      .append(PathCharacters.VERTICAL.value())
      .append(System.lineSeparator())
      .append(LetterCharacters.A)
      .toString();

    AsciiMap map = AsciiMap.ofString(asciiMapString);
    Position position = new Position(1, 0);

    assertEquals(PathDirection.UP,
                 PathDirection.getPathDirection(map, position, PathDirection.LEFT));

    assertEquals(PathDirection.UP,
                 PathDirection.getPathDirection(map, position, PathDirection.RIGHT));
  }

  @Test public void test_getPathDirection_LetterTurnDown() throws CodeChallengeException {
    String asciiMapString = new StringBuilder()
      .append(LetterCharacters.A)
      .append(System.lineSeparator())
      .append(PathCharacters.VERTICAL.value())
      .toString();

    AsciiMap map = AsciiMap.ofString(asciiMapString);
    Position position = new Position(0, 0);

    assertEquals(PathDirection.DOWN,
                 PathDirection.getPathDirection(map, position, PathDirection.LEFT));

    assertEquals(PathDirection.DOWN,
                 PathDirection.getPathDirection(map, position, PathDirection.RIGHT));
  }

  @Test public void test_getPathDirection_LetterTurnLeft() throws CodeChallengeException {
    String asciiMapString = new StringBuilder()
      .append(PathCharacters.HORIZONTAL.value())
      .append(LetterCharacters.A)
      .toString();

    AsciiMap map = AsciiMap.ofString(asciiMapString);
    Position position = new Position(0, 1);

    assertEquals(PathDirection.LEFT,
                 PathDirection.getPathDirection(map, position, PathDirection.UP));

    assertEquals(PathDirection.LEFT,
                 PathDirection.getPathDirection(map, position, PathDirection.DOWN));
  }

  @Test public void test_getPathDirection_LetterTurnRight() throws CodeChallengeException {
    String asciiMapString = new StringBuilder()
      .append(LetterCharacters.A)
      .append(PathCharacters.HORIZONTAL.value())
      .toString();

    AsciiMap map = AsciiMap.ofString(asciiMapString);
    Position position = new Position(0, 0);

    assertEquals(PathDirection.RIGHT,
                 PathDirection.getPathDirection(map, position, PathDirection.UP));

    assertEquals(PathDirection.RIGHT,
                 PathDirection.getPathDirection(map, position, PathDirection.DOWN));
  }

  @Test public void test_getPathDirection_LetterTForkHorizontal() throws CodeChallengeException {
    String asciiMapString = new StringBuilder()
      .append(PathCharacters.VERTICAL.value())
      .append(System.lineSeparator())
      .append(LetterCharacters.A)
      .append(System.lineSeparator())
      .append(PathCharacters.VERTICAL.value())
      .toString();

    AsciiMap map = AsciiMap.ofString(asciiMapString);
    Position position = new Position(1, 0);

    Assertions.assertThrows(PathDirection.TForkException.class, () -> {
      PathDirection.getPathDirection(map, position, PathDirection.LEFT);
    });

    Assertions.assertThrows(PathDirection.TForkException.class, () -> {
      PathDirection.getPathDirection(map, position, PathDirection.RIGHT);
    });
  }

  @Test public void test_getPathDirection_LetterTForkVertical() throws CodeChallengeException {
    String asciiMapString = new StringBuilder()
      .append(PathCharacters.HORIZONTAL.value())
      .append(LetterCharacters.A)
      .append(PathCharacters.HORIZONTAL.value())
      .toString();

    AsciiMap map = AsciiMap.ofString(asciiMapString);
    Position position = new Position(0, 1);

    Assertions.assertThrows(PathDirection.TForkException.class, () -> {
      PathDirection.getPathDirection(map, position, PathDirection.UP);
    });

    Assertions.assertThrows(PathDirection.TForkException.class, () -> {
      PathDirection.getPathDirection(map, position, PathDirection.DOWN);
    });
  }
}
