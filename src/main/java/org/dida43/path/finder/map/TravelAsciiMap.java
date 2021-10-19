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

    Coordinates travelCoordinates = asciiMap.startCoordinates();
    Coordinates endTravelCoordinates = asciiMap.endCoordinates();

    asciiMap.visitCoordinates(travelCoordinates);

    pathAsCharacters.append(asciiMap.getCharForCoordinates(travelCoordinates));
    Direction travelDirection = Direction.findStartingDirection(asciiMap, travelCoordinates);

    while (!travelCoordinates.equals(endTravelCoordinates)) {
      travelCoordinates = travelByDirection(travelCoordinates, travelDirection);
      char currentCharacter = asciiMap.getCharForCoordinates(travelCoordinates);

      pathAsCharacters.append(currentCharacter);
      if (Letters.isLetter(currentCharacter) && !asciiMap.areCoordinatesVisited(travelCoordinates))
        letters.append(currentCharacter);

      asciiMap.visitCoordinates(travelCoordinates);
      travelDirection = Direction.findDirection(asciiMap, travelCoordinates, travelDirection);
    }
    return new Solution(pathAsCharacters.toString(), letters.toString());
  }

  private Coordinates travelByDirection(Coordinates coordinates, Direction currentDirection)
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
