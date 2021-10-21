package org.dida43.path.finder.enums;

import org.dida43.path.finder.exceptions.path.FakeTurnPathException;
import org.dida43.path.finder.exceptions.path.MultipleStartingPathException;
import org.dida43.path.finder.exceptions.path.NoStartingPathException;
import org.dida43.path.finder.exceptions.path.TForkPathException;
import org.dida43.path.finder.map.AsciiMap;
import org.dida43.path.finder.pojos.Coordinates;

public enum Path {
  UP, DOWN, LEFT, RIGHT;

  public static Path findStartingPath(AsciiMap asciiMap, Coordinates coordinates)
    throws NoStartingPathException, MultipleStartingPathException
  {
    Path startingPath = null;
    int noOfTraversableChars = 0;
    if (charTraversable(asciiMap.getCharForCoordinates(coordinates.up()))) {
      noOfTraversableChars++;
      startingPath = Path.UP;
    }
    if (charTraversable(asciiMap.getCharForCoordinates(coordinates.down()))) {
      noOfTraversableChars++;
      startingPath = Path.DOWN;
    }
    if (charTraversable(asciiMap.getCharForCoordinates(coordinates.left()))) {
      noOfTraversableChars++;
      startingPath = Path.LEFT;
    }
    if (charTraversable(asciiMap.getCharForCoordinates(coordinates.right()))) {
      noOfTraversableChars++;
      startingPath = Path.RIGHT;
    }

    if (noOfTraversableChars < 1)
      throw new NoStartingPathException(coordinates);
    if (noOfTraversableChars > 1)
      throw new MultipleStartingPathException(coordinates);

    return startingPath;
  }

  public static Path findPath(
    AsciiMap asciiMap, Coordinates position, Path currentPath)
    throws TForkPathException, FakeTurnPathException
  {
    char inspected = asciiMap.getCharForCoordinates(position);
    if (inspected == Characters.TURN.value())
      return findPathFromTurn(asciiMap, position, currentPath);
    if (Letters.isLetter(inspected))
      return findPathFromLetter(asciiMap, position, currentPath);
    return currentPath;
  }

  private static Path findPathFromTurn(
    AsciiMap asciiMap, Coordinates coordinates, Path currentPath)
    throws TForkPathException, FakeTurnPathException
  {
    if (currentPath == Path.UP || currentPath == Path.DOWN) {
      char leftChar = asciiMap.getCharForCoordinates(coordinates.left());
      char rightChar = asciiMap.getCharForCoordinates(coordinates.right());
      if (charTraversable(leftChar) && charTraversable(rightChar))
        throw new TForkPathException(coordinates);
      if (charTraversable(leftChar))
        return Path.LEFT;
      if (charTraversable(rightChar))
        return Path.RIGHT;
    }
    if (currentPath == Path.LEFT || currentPath == Path.RIGHT) {
      char upChar = asciiMap.getCharForCoordinates(coordinates.up());
      char downChar = asciiMap.getCharForCoordinates(coordinates.down());
      if (charTraversable(upChar) && charTraversable(downChar))
        throw new TForkPathException(coordinates);
      if (charTraversable(upChar))
        return Path.UP;
      if (charTraversable(downChar))
        return Path.DOWN;
    }
    throw new FakeTurnPathException(coordinates);
  }

  private static Path findPathFromLetter(
    AsciiMap asciiMap, Coordinates coordinates, Path currentPath)
    throws TForkPathException, FakeTurnPathException
  {
    //todo:write test for letter on t intersection
    char upChar = asciiMap.getCharForCoordinates(coordinates.up());
    char downChar = asciiMap.getCharForCoordinates(coordinates.down());
    char leftChar = asciiMap.getCharForCoordinates(coordinates.left());
    char rightChar = asciiMap.getCharForCoordinates(coordinates.right());
    if (currentPath == Path.UP && charTraversable(upChar))
      return currentPath;
    if (currentPath == Path.DOWN && charTraversable(downChar))
      return currentPath;
    if (currentPath == Path.LEFT && charTraversable(leftChar))
      return currentPath;
    if (currentPath == Path.RIGHT && charTraversable(rightChar))
      return currentPath;

    return findPathFromTurn(asciiMap, coordinates, currentPath);
  }

  public static boolean charTraversable(char c) {
    return Letters.isLetter(c) || Characters.isCharacter(c);
  }
}
