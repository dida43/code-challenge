package org.dida43.map.walker.enums;

import org.dida43.map.walker.exceptions.path.FakeTurnPathDirectionException;
import org.dida43.map.walker.exceptions.path.MultipleStartingPathDirectionException;
import org.dida43.map.walker.exceptions.path.NoStartingPathDirectionException;
import org.dida43.map.walker.exceptions.path.TForkPathDirectionException;
import org.dida43.map.walker.map.AsciiMap;
import org.dida43.map.walker.pojos.Position;

public enum PathDirection {
  UP, DOWN, LEFT, RIGHT;

  public static PathDirection getStartingPathDirection(AsciiMap asciiMap, Position position)
    throws NoStartingPathDirectionException, MultipleStartingPathDirectionException
  {
    PathDirection startingPathDirection = null;
    int noOfTraversableChars = 0;
    if (!isPathBroken(asciiMap.getCharForPosition(position.up()))) {
      noOfTraversableChars++;
      startingPathDirection = PathDirection.UP;
    }
    if (!isPathBroken(asciiMap.getCharForPosition(position.down()))) {
      noOfTraversableChars++;
      startingPathDirection = PathDirection.DOWN;
    }
    if (!isPathBroken(asciiMap.getCharForPosition(position.left()))) {
      noOfTraversableChars++;
      startingPathDirection = PathDirection.LEFT;
    }
    if (!isPathBroken(asciiMap.getCharForPosition(position.right()))) {
      noOfTraversableChars++;
      startingPathDirection = PathDirection.RIGHT;
    }

    if (noOfTraversableChars < 1)
      throw new NoStartingPathDirectionException(position);
    if (noOfTraversableChars > 1)
      throw new MultipleStartingPathDirectionException(position);

    return startingPathDirection;
  }

  public static PathDirection getPathDirection(
    AsciiMap asciiMap, Position position, PathDirection currentPathDirection)
    throws TForkPathDirectionException, FakeTurnPathDirectionException
  {
    char inspected = asciiMap.getCharForPosition(position);
    if (inspected == PathCharacters.TURN.value())
      return getPathDirectionForTurn(asciiMap, position, currentPathDirection);
    if (LetterCharacters.isLetter(inspected))
      return getPathDirectionForLetter(asciiMap, position, currentPathDirection);
    return currentPathDirection;
  }

  private static PathDirection getPathDirectionForTurn(
    AsciiMap asciiMap, Position position, PathDirection currentPathDirection)
    throws TForkPathDirectionException, FakeTurnPathDirectionException
  {
    if (currentPathDirection == PathDirection.UP || currentPathDirection == PathDirection.DOWN) {
      char leftChar = asciiMap.getCharForPosition(position.left());
      char rightChar = asciiMap.getCharForPosition(position.right());
      if (!isPathBroken(leftChar) && !isPathBroken(rightChar))
        throw new TForkPathDirectionException(position);
      if (!isPathBroken(leftChar))
        return PathDirection.LEFT;
      if (!isPathBroken(rightChar))
        return PathDirection.RIGHT;
    }
    if (currentPathDirection == PathDirection.LEFT || currentPathDirection == PathDirection.RIGHT) {
      char upChar = asciiMap.getCharForPosition(position.up());
      char downChar = asciiMap.getCharForPosition(position.down());
      if (!isPathBroken(upChar) && !isPathBroken(downChar))
        throw new TForkPathDirectionException(position);
      if (!isPathBroken(upChar))
        return PathDirection.UP;
      if (!isPathBroken(downChar))
        return PathDirection.DOWN;
    }
    throw new FakeTurnPathDirectionException(position);
  }

  private static PathDirection getPathDirectionForLetter(
    AsciiMap asciiMap, Position position, PathDirection currentPathDirection)
    throws TForkPathDirectionException, FakeTurnPathDirectionException
  {
    //todo:write test for letter on t intersection
    char upChar = asciiMap.getCharForPosition(position.up());
    char downChar = asciiMap.getCharForPosition(position.down());
    char leftChar = asciiMap.getCharForPosition(position.left());
    char rightChar = asciiMap.getCharForPosition(position.right());
    if (currentPathDirection == PathDirection.UP && !isPathBroken(upChar))
      return currentPathDirection;
    if (currentPathDirection == PathDirection.DOWN && !isPathBroken(downChar))
      return currentPathDirection;
    if (currentPathDirection == PathDirection.LEFT && !isPathBroken(leftChar))
      return currentPathDirection;
    if (currentPathDirection == PathDirection.RIGHT && !isPathBroken(rightChar))
      return currentPathDirection;

    return getPathDirectionForTurn(asciiMap, position, currentPathDirection);
  }

  public static boolean isPathBroken(char c) {
    return !LetterCharacters.isLetter(c) && !PathCharacters.isPathCharacter(c);
  }
}
