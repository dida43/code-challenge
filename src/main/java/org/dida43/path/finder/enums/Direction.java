package org.dida43.path.finder.enums;

import org.dida43.path.finder.map.AsciiMap;
import org.dida43.path.finder.pojos.Coordinates;

public enum Direction {
  UP, DOWN, LEFT, RIGHT;

  public static Direction findStartingDirection(AsciiMap asciiMap, Coordinates coordinates)
    throws Exception
  {
    Direction startingDirection = null;
    int noOfTraversableChars = 0;
    if (charTraversable(asciiMap.getCharForCoordinates(coordinates.up()))) {
      noOfTraversableChars++;
      startingDirection = Direction.UP;
    }
    if (charTraversable(asciiMap.getCharForCoordinates(coordinates.down()))) {
      noOfTraversableChars++;
      startingDirection = Direction.DOWN;
    }
    if (charTraversable(asciiMap.getCharForCoordinates(coordinates.left()))) {
      noOfTraversableChars++;
      startingDirection = Direction.LEFT;
    }
    if (charTraversable(asciiMap.getCharForCoordinates(coordinates.right()))) {
      noOfTraversableChars++;
      startingDirection = Direction.RIGHT;
    }

    if (noOfTraversableChars != 1)
      throw new Exception("Multiple or no starting paths");

    return startingDirection;
  }

  public static Direction findDirection(
    AsciiMap asciiMap, Coordinates position, Direction currentDirection) throws Exception
  {
    char inspected = asciiMap.getCharForCoordinates(position);
    if (inspected == Characters.TURN.value())
      return handleTurn(asciiMap, position, currentDirection);
    if (Letters.isLetter(inspected))
      return handleLetter(asciiMap, position, currentDirection);
    return currentDirection;
  }

  private static Direction handleTurn(
    AsciiMap asciiMap, Coordinates coordinates, Direction currentDirection) throws Exception
  {
    if (currentDirection == Direction.UP || currentDirection == Direction.DOWN) {
      char leftChar = asciiMap.getCharForCoordinates(coordinates.left());
      char rightChar = asciiMap.getCharForCoordinates(coordinates.right());
      if (charTraversable(leftChar) && charTraversable(rightChar))
        throw new Exception("T intersection error");
      if (charTraversable(leftChar))
        return Direction.LEFT;
      if (charTraversable(rightChar))
        return Direction.RIGHT;
    }
    if (currentDirection == Direction.LEFT || currentDirection == Direction.RIGHT) {
      char upChar = asciiMap.getCharForCoordinates(coordinates.up());
      char downChar = asciiMap.getCharForCoordinates(coordinates.down());
      if (charTraversable(upChar) && charTraversable(downChar))
        throw new Exception("T intersection error");
      if (charTraversable(upChar))
        return Direction.UP;
      if (charTraversable(downChar))
        return Direction.DOWN;
    }
    throw new Exception("Fake turn");
  }

  private static Direction handleLetter(
    AsciiMap asciiMap, Coordinates coordinates, Direction currentDirection) throws Exception
  {
    char upChar = asciiMap.getCharForCoordinates(coordinates.up());
    char downChar = asciiMap.getCharForCoordinates(coordinates.down());
    char leftChar = asciiMap.getCharForCoordinates(coordinates.left());
    char rightChar = asciiMap.getCharForCoordinates(coordinates.right());
    if (currentDirection == Direction.UP && charTraversable(upChar))
      return currentDirection;
    if (currentDirection == Direction.DOWN && charTraversable(downChar))
      return currentDirection;
    if (currentDirection == Direction.LEFT && charTraversable(leftChar))
      return currentDirection;
    if (currentDirection == Direction.RIGHT && charTraversable(rightChar))
      return currentDirection;

    return handleTurn(asciiMap, coordinates, currentDirection);
  }

  private static boolean charTraversable(char c) {
    return Letters.isLetter(c) || Characters.isCharacter(c);
  }
}
