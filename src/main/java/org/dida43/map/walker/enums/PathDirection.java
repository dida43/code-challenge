package org.dida43.map.walker.enums;

import org.dida43.map.walker.CodeChallengeException;
import org.dida43.map.walker.map.AsciiMap;
import org.dida43.map.walker.map.AsciiMapWalker;
import org.dida43.map.walker.pojos.Position;

public enum PathDirection {
  UP, DOWN, LEFT, RIGHT;

  public static PathDirection getStartingPathDirection(AsciiMap asciiMap, Position position)
    throws NoStartException, MultipleStartException
  {
    PathDirection startingPathDirection = null;
    int noOfWalkableChars = 0;
    if (AsciiMapWalker.canWalk(asciiMap.getCharForPosition(position.up()))) {
      noOfWalkableChars++;
      startingPathDirection = PathDirection.UP;
    }
    if (AsciiMapWalker.canWalk(asciiMap.getCharForPosition(position.down()))) {
      noOfWalkableChars++;
      startingPathDirection = PathDirection.DOWN;
    }
    if (AsciiMapWalker.canWalk(asciiMap.getCharForPosition(position.left()))) {
      noOfWalkableChars++;
      startingPathDirection = PathDirection.LEFT;
    }
    if (AsciiMapWalker.canWalk(asciiMap.getCharForPosition(position.right()))) {
      noOfWalkableChars++;
      startingPathDirection = PathDirection.RIGHT;
    }

    if (noOfWalkableChars < 1)
      throw new NoStartException(position);
    if (noOfWalkableChars > 1)
      throw new MultipleStartException(position);

    return startingPathDirection;
  }

  public static PathDirection getPathDirection(
    AsciiMap asciiMap, Position position, PathDirection currentPathDirection)
    throws TForkException, FakeTurnException
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
    throws TForkException, FakeTurnException
  {
    if (currentPathDirection == PathDirection.UP || currentPathDirection == PathDirection.DOWN) {
      char leftChar = asciiMap.getCharForPosition(position.left());
      char rightChar = asciiMap.getCharForPosition(position.right());
      if (AsciiMapWalker.canWalk(leftChar) && AsciiMapWalker.canWalk(rightChar))
        throw new TForkException(position);
      if (AsciiMapWalker.canWalk(leftChar))
        return PathDirection.LEFT;
      if (AsciiMapWalker.canWalk(rightChar))
        return PathDirection.RIGHT;
    }
    if (currentPathDirection == PathDirection.LEFT || currentPathDirection == PathDirection.RIGHT) {
      char upChar = asciiMap.getCharForPosition(position.up());
      char downChar = asciiMap.getCharForPosition(position.down());
      if (AsciiMapWalker.canWalk(upChar) && AsciiMapWalker.canWalk(downChar))
        throw new TForkException(position);
      if (AsciiMapWalker.canWalk(upChar))
        return PathDirection.UP;
      if (AsciiMapWalker.canWalk(downChar))
        return PathDirection.DOWN;
    }
    throw new FakeTurnException(position);
  }

  private static PathDirection getPathDirectionForLetter(
    AsciiMap asciiMap, Position position, PathDirection currentPathDirection)
    throws TForkException, FakeTurnException
  {
    char upChar = asciiMap.getCharForPosition(position.up());
    char downChar = asciiMap.getCharForPosition(position.down());
    char leftChar = asciiMap.getCharForPosition(position.left());
    char rightChar = asciiMap.getCharForPosition(position.right());
    if (currentPathDirection == PathDirection.UP && AsciiMapWalker.canWalk(upChar))
      return currentPathDirection;
    if (currentPathDirection == PathDirection.DOWN && AsciiMapWalker.canWalk(downChar))
      return currentPathDirection;
    if (currentPathDirection == PathDirection.LEFT && AsciiMapWalker.canWalk(leftChar))
      return currentPathDirection;
    if (currentPathDirection == PathDirection.RIGHT && AsciiMapWalker.canWalk(rightChar))
      return currentPathDirection;

    return getPathDirectionForTurn(asciiMap, position, currentPathDirection);
  }

  public static class FakeTurnException extends CodeChallengeException {
    public FakeTurnException(Position position) {
      super("Position: "+position.toString()+" contain fake turn.");
    }
  }

  public static class MultipleStartException extends CodeChallengeException {
    public MultipleStartException(Position position) {
      super("Multiple paths from position: "+position.toString()+" instead of one.");
    }
  }

  public static class NoStartException extends CodeChallengeException {
    public NoStartException(Position position) {
      super("No starting path from position: "+position.toString());
    }
  }

  public static class TForkException extends CodeChallengeException {
    public TForkException(Position position) {
      super("Position: "+position.toString()+" contain T fork. Cannot determine path");
    }
  }
}
