package org.dida43.path.finder.map;

import org.dida43.path.finder.enums.Characters;
import org.dida43.path.finder.enums.Direction;
import org.dida43.path.finder.enums.Letters;
import org.dida43.path.finder.pojos.Coordinates;
import org.dida43.path.finder.pojos.Solution;

public class TravelAsciiMap {

  private final AsciiMap asciiMap;

  public TravelAsciiMap(AsciiMap asciiMap) {
    this.asciiMap = asciiMap;
  }

  public Solution findSolution() throws Exception {
    StringBuilder pathAsCharacters = new StringBuilder();
    StringBuilder letters = new StringBuilder();

    Coordinates currentCoordinates = asciiMap.getStartCoordinates();
    Coordinates endCoordinates = asciiMap.getEndCoordinates();

    asciiMap.updateMapOfCoordinatesVisited(currentCoordinates);

    pathAsCharacters.append(asciiMap.getCharForCoordinates(currentCoordinates));
    Direction currentDirection = findStartingDirection(currentCoordinates);

    while (!currentCoordinates.equals(endCoordinates)) {
      currentCoordinates = updateCoordinates(currentCoordinates, currentDirection);
      char currentCharacter = asciiMap.getCharForCoordinates(currentCoordinates);
      pathAsCharacters.append(currentCharacter);
      if (Letters.isLetter(currentCharacter) && !asciiMap.areCoordinatesVisited(currentCoordinates))
        letters.append(asciiMap.getCharForCoordinates(currentCoordinates));
      asciiMap.updateMapOfCoordinatesVisited(currentCoordinates);
      currentDirection = findDirection(currentCoordinates, currentDirection);
    }
    return new Solution(pathAsCharacters.toString(), letters.toString());
  }

  private Direction findDirection(Coordinates position, Direction currentDirection) throws Exception
  {
    char inspected = asciiMap.getCharForCoordinates(position);
    if (inspected == Characters.TURN.value())
      return handleTurn(position, currentDirection);
    if (Letters.isLetter(inspected))
      return handleLetter(position, currentDirection);
    return currentDirection;
  }

  private Direction handleTurn(Coordinates coordinates, Direction currentDirection) throws Exception
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

  private Direction handleLetter(Coordinates coordinates, Direction currentDirection)
    throws Exception
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

    return handleTurn(coordinates, currentDirection);
  }

  private boolean charTraversable(char c) {
    return Letters.isLetter(c) || Characters.isCharacter(c);
  }

  private Coordinates updateCoordinates(Coordinates coordinates, Direction currentDirection)
    throws Exception
  {
    switch (currentDirection) {
      case UP:
        return coordinates.up();
      case DOWN:
        return coordinates.down();
      case LEFT:
        return coordinates.left();
      case RIGHT:
        return coordinates.right();
    }
    throw new Exception("No direction found!");
  }

  private Direction findStartingDirection(Coordinates coordinates) throws Exception {
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
}
