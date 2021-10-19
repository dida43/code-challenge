package org.dida43.path.finder.map;

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
    Direction currentDirection = Direction.findStartingDirection(asciiMap, currentCoordinates);

    while (!currentCoordinates.equals(endCoordinates)) {
      currentCoordinates = updateCoordinates(currentCoordinates, currentDirection);
      char currentCharacter = asciiMap.getCharForCoordinates(currentCoordinates);
      pathAsCharacters.append(currentCharacter);
      if (Letters.isLetter(currentCharacter) && !asciiMap.areCoordinatesVisited(currentCoordinates))
        letters.append(asciiMap.getCharForCoordinates(currentCoordinates));
      asciiMap.updateMapOfCoordinatesVisited(currentCoordinates);
      currentDirection = Direction.findDirection(asciiMap, currentCoordinates, currentDirection);
    }
    return new Solution(pathAsCharacters.toString(), letters.toString());
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
}
