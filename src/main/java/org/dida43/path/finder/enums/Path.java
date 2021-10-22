package org.dida43.path.finder.enums;

import org.dida43.path.finder.exceptions.path.FakeTurnPathException;
import org.dida43.path.finder.exceptions.path.MultipleStartingPathException;
import org.dida43.path.finder.exceptions.path.NoStartingPathException;
import org.dida43.path.finder.exceptions.path.TForkPathException;
import org.dida43.path.finder.map.AsciiMap;
import org.dida43.path.finder.pojos.Position;

public enum Path {
  UP, DOWN, LEFT, RIGHT;

  public static Path findStartingPath(AsciiMap asciiMap, Position position)
    throws NoStartingPathException, MultipleStartingPathException
  {
    Path startingPath = null;
    int noOfTraversableChars = 0;
    if (charTraversable(asciiMap.getCharForPosition(position.up()))) {
      noOfTraversableChars++;
      startingPath = Path.UP;
    }
    if (charTraversable(asciiMap.getCharForPosition(position.down()))) {
      noOfTraversableChars++;
      startingPath = Path.DOWN;
    }
    if (charTraversable(asciiMap.getCharForPosition(position.left()))) {
      noOfTraversableChars++;
      startingPath = Path.LEFT;
    }
    if (charTraversable(asciiMap.getCharForPosition(position.right()))) {
      noOfTraversableChars++;
      startingPath = Path.RIGHT;
    }

    if (noOfTraversableChars < 1)
      throw new NoStartingPathException(position);
    if (noOfTraversableChars > 1)
      throw new MultipleStartingPathException(position);

    return startingPath;
  }

  public static Path findPath(
    AsciiMap asciiMap, Position position, Path currentPath)
    throws TForkPathException, FakeTurnPathException
  {
    char inspected = asciiMap.getCharForPosition(position);
    if (inspected == Characters.TURN.value())
      return findPathFromTurn(asciiMap, position, currentPath);
    if (Letters.isLetter(inspected))
      return findPathFromLetter(asciiMap, position, currentPath);
    return currentPath;
  }

  private static Path findPathFromTurn(
    AsciiMap asciiMap, Position position, Path currentPath)
    throws TForkPathException, FakeTurnPathException
  {
    if (currentPath == Path.UP || currentPath == Path.DOWN) {
      char leftChar = asciiMap.getCharForPosition(position.left());
      char rightChar = asciiMap.getCharForPosition(position.right());
      if (charTraversable(leftChar) && charTraversable(rightChar))
        throw new TForkPathException(position);
      if (charTraversable(leftChar))
        return Path.LEFT;
      if (charTraversable(rightChar))
        return Path.RIGHT;
    }
    if (currentPath == Path.LEFT || currentPath == Path.RIGHT) {
      char upChar = asciiMap.getCharForPosition(position.up());
      char downChar = asciiMap.getCharForPosition(position.down());
      if (charTraversable(upChar) && charTraversable(downChar))
        throw new TForkPathException(position);
      if (charTraversable(upChar))
        return Path.UP;
      if (charTraversable(downChar))
        return Path.DOWN;
    }
    throw new FakeTurnPathException(position);
  }

  private static Path findPathFromLetter(
    AsciiMap asciiMap, Position position, Path currentPath)
    throws TForkPathException, FakeTurnPathException
  {
    //todo:write test for letter on t intersection
    char upChar = asciiMap.getCharForPosition(position.up());
    char downChar = asciiMap.getCharForPosition(position.down());
    char leftChar = asciiMap.getCharForPosition(position.left());
    char rightChar = asciiMap.getCharForPosition(position.right());
    if (currentPath == Path.UP && charTraversable(upChar))
      return currentPath;
    if (currentPath == Path.DOWN && charTraversable(downChar))
      return currentPath;
    if (currentPath == Path.LEFT && charTraversable(leftChar))
      return currentPath;
    if (currentPath == Path.RIGHT && charTraversable(rightChar))
      return currentPath;

    return findPathFromTurn(asciiMap, position, currentPath);
  }

  public static boolean charTraversable(char c) {
    return Letters.isLetter(c) || Characters.isCharacter(c);
  }
}
