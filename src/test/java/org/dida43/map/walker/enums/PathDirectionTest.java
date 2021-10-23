package org.dida43.map.walker.enums;

import org.dida43.map.walker.exceptions.AsciiMapException;
import org.dida43.map.walker.exceptions.path.FakeTurnPathDirectionException;
import org.dida43.map.walker.exceptions.path.MultipleStartingPathDirectionException;
import org.dida43.map.walker.exceptions.path.NoStartingPathDirectionException;
import org.dida43.map.walker.exceptions.path.TForkPathDirectionException;
import org.dida43.map.walker.map.AsciiMap;
import org.dida43.map.walker.pojos.Position;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PathDirectionTest {

  @Test public void startingDirectionUp() throws AsciiMapException {
    String asciiMapString = new StringBuilder()
      .append(PathCharacters.VERTICAL.value())
      .append(System.lineSeparator())
      .append(NonPathCharacters.START.value())
      .toString();

    AsciiMap map = AsciiMap.ofString(asciiMapString);
    Position position = new Position(1, 0);

    assertEquals(PathDirection.UP, PathDirection.getStartingPathDirection(map, position));
  }

  @Test public void startingDirectionDown() throws AsciiMapException {
    String asciiMapString = new StringBuilder()
      .append(NonPathCharacters.START.value())
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
      .append(NonPathCharacters.START.value())
      .toString();

    AsciiMap map = AsciiMap.ofString(asciiMapString);
    Position position = new Position(0, 1);

    assertEquals(PathDirection.LEFT, PathDirection.getStartingPathDirection(map, position));
  }

  @Test public void startingDirectionRight() throws AsciiMapException {
    String asciiMapString = new StringBuilder()
      .append(NonPathCharacters.START.value())
      .append(PathCharacters.HORIZONTAL.value())
      .toString();

    AsciiMap map = AsciiMap.ofString(asciiMapString);
    Position position = new Position(0, 0);

    assertEquals(PathDirection.RIGHT, PathDirection.getStartingPathDirection(map, position));
  }

  @Test public void startingDirectionMultiple() throws AsciiMapException {
    String asciiMapString = new StringBuilder()
      .append(NonPathCharacters.START.value())
      .append(PathCharacters.HORIZONTAL.value())
      .append(System.lineSeparator())
      .append(PathCharacters.VERTICAL.value())
      .toString();

    AsciiMap map = AsciiMap.ofString(asciiMapString);
    Position position = new Position(0, 0);

    Assertions.assertThrows(MultipleStartingPathDirectionException.class, () -> {
      PathDirection.getStartingPathDirection(map, position);
    });
  }

  @Test public void startingDirectionNone() throws AsciiMapException {
    String asciiMapString = new StringBuilder().append(NonPathCharacters.START.value()).toString();

    AsciiMap map = AsciiMap.ofString(asciiMapString);
    Position position = new Position(0, 0);

    Assertions.assertThrows(NoStartingPathDirectionException.class, () -> {
      PathDirection.getStartingPathDirection(map, position);
    });
  }

  @Test public void startingDirectionIgnoreNonPath() throws AsciiMapException {
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

  @Test public void directionGoStraightHorizontal() throws AsciiMapException {
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

  @Test public void directionGoStraightVertical() throws AsciiMapException {
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

  @Test public void directionGoStraightIntersectionLeftRight() throws AsciiMapException {
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

  @Test public void directionGoStraightIntersectionRightLeft() throws AsciiMapException {
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

  @Test public void directionGoStraightIntersectionUpDown() throws AsciiMapException {
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

  @Test public void directionGoStraightIntersectionDownUp() throws AsciiMapException {
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

  @Test public void directionTurnHorizontalUp() throws AsciiMapException {
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

  @Test public void directionTurnHorizontalDown() throws AsciiMapException {
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

  @Test public void directionTurnVerticalLeft() throws AsciiMapException {
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

  @Test public void directionTurnVerticalRight() throws AsciiMapException {
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

  @Test public void directionFakeTurnHorizontal() throws AsciiMapException {
    String asciiMapString = new StringBuilder()
      .append(PathCharacters.HORIZONTAL.value())
      .append(PathCharacters.TURN.value())
      .append(PathCharacters.HORIZONTAL.value())
      .toString();

    AsciiMap map = AsciiMap.ofString(asciiMapString);
    Position position = new Position(0, 1);

    Assertions.assertThrows(FakeTurnPathDirectionException.class, () -> {
      PathDirection.getPathDirection(map, position, PathDirection.LEFT);
    });

    Assertions.assertThrows(FakeTurnPathDirectionException.class, () -> {
      PathDirection.getPathDirection(map, position, PathDirection.RIGHT);
    });
  }

  @Test public void directionFakeTurnVertical() throws AsciiMapException {
    String asciiMapString = new StringBuilder()
      .append(PathCharacters.VERTICAL.value())
      .append(System.lineSeparator())
      .append(PathCharacters.TURN.value())
      .append(System.lineSeparator())
      .append(PathCharacters.VERTICAL.value())
      .toString();

    AsciiMap map = AsciiMap.ofString(asciiMapString);
    Position position = new Position(1, 0);

    Assertions.assertThrows(FakeTurnPathDirectionException.class, () -> {
      PathDirection.getPathDirection(map, position, PathDirection.UP);
    });

    Assertions.assertThrows(FakeTurnPathDirectionException.class, () -> {
      PathDirection.getPathDirection(map, position, PathDirection.DOWN);
    });
  }

  @Test public void directionTForkHorizontal() throws AsciiMapException {
    String asciiMapString = new StringBuilder()
      .append(PathCharacters.VERTICAL.value())
      .append(System.lineSeparator())
      .append(PathCharacters.TURN.value())
      .append(System.lineSeparator())
      .append(PathCharacters.VERTICAL.value())
      .toString();

    AsciiMap map = AsciiMap.ofString(asciiMapString);
    Position position = new Position(1, 0);

    Assertions.assertThrows(TForkPathDirectionException.class, () -> {
      PathDirection.getPathDirection(map, position, PathDirection.LEFT);
    });

    Assertions.assertThrows(TForkPathDirectionException.class, () -> {
      PathDirection.getPathDirection(map, position, PathDirection.RIGHT);
    });
  }

  @Test public void directionTForkVertical() throws AsciiMapException {
    String asciiMapString = new StringBuilder()
      .append(PathCharacters.HORIZONTAL.value())
      .append(PathCharacters.TURN.value())
      .append(PathCharacters.HORIZONTAL.value())
      .toString();

    AsciiMap map = AsciiMap.ofString(asciiMapString);
    Position position = new Position(0, 1);

    Assertions.assertThrows(TForkPathDirectionException.class, () -> {
      PathDirection.getPathDirection(map, position, PathDirection.UP);
    });

    Assertions.assertThrows(TForkPathDirectionException.class, () -> {
      PathDirection.getPathDirection(map, position, PathDirection.DOWN);
    });
  }

  @Test public void directionGoStraightLetterLeftRight() throws AsciiMapException {
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

  @Test public void directionGoStraightLetterRightLeft() throws AsciiMapException {
    String asciiMapString = new StringBuilder()
      .append(LetterCharacters.A)
      .append(LetterCharacters.A)
      .append(PathCharacters.HORIZONTAL.value())
      .toString();

    AsciiMap map = AsciiMap.ofString(asciiMapString);
    Position position = new Position(0, 1);

    PathDirection currentDirection = PathDirection.LEFT;
    assertEquals(currentDirection, PathDirection.getPathDirection(map, position, currentDirection));
  }

  @Test public void directionGoStraightLetterUpDown() throws AsciiMapException {
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

  @Test public void directionGoStraightLetterDownUp() throws AsciiMapException {
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

  @Test public void directionLetterTurnHorizontalUp() throws AsciiMapException {
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

  @Test public void directionLetterTurnHorizontalDown() throws AsciiMapException {
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

  @Test public void directionLetterTurnVerticalLeft() throws AsciiMapException {
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

  @Test public void directionLetterTurnVerticalRight() throws AsciiMapException {
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

  @Test public void directionLetterTForkHorizontal() throws AsciiMapException {
    String asciiMapString = new StringBuilder()
      .append(PathCharacters.VERTICAL.value())
      .append(System.lineSeparator())
      .append(LetterCharacters.A)
      .append(System.lineSeparator())
      .append(PathCharacters.VERTICAL.value())
      .toString();

    AsciiMap map = AsciiMap.ofString(asciiMapString);
    Position position = new Position(1, 0);

    Assertions.assertThrows(TForkPathDirectionException.class, () -> {
      PathDirection.getPathDirection(map, position, PathDirection.LEFT);
    });

    Assertions.assertThrows(TForkPathDirectionException.class, () -> {
      PathDirection.getPathDirection(map, position, PathDirection.RIGHT);
    });
  }

  @Test public void directionLetterTForkVertical() throws AsciiMapException {
    String asciiMapString = new StringBuilder()
      .append(PathCharacters.HORIZONTAL.value())
      .append(LetterCharacters.A)
      .append(PathCharacters.HORIZONTAL.value())
      .toString();

    AsciiMap map = AsciiMap.ofString(asciiMapString);
    Position position = new Position(0, 1);

    Assertions.assertThrows(TForkPathDirectionException.class, () -> {
      PathDirection.getPathDirection(map, position, PathDirection.UP);
    });

    Assertions.assertThrows(TForkPathDirectionException.class, () -> {
      PathDirection.getPathDirection(map, position, PathDirection.DOWN);
    });
  }
}
