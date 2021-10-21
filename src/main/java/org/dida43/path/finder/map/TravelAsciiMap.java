package org.dida43.path.finder.map;

import org.dida43.path.finder.enums.Path;
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

    pathAsCharacters.append(asciiMap.getCharForCoordinates(travelCoordinates));

    asciiMap.visitCoordinates(travelCoordinates);
    Path travelPath = Path.findStartingPath(asciiMap, travelCoordinates);

    while (!travelCoordinates.equals(endTravelCoordinates)) {
      travelCoordinates = travelByPath(travelCoordinates, travelPath);
      char currentCharacter = asciiMap.getCharForCoordinates(travelCoordinates);

      pathAsCharacters.append(currentCharacter);
      if (Letters.isLetter(currentCharacter) && !asciiMap.areCoordinatesVisited(travelCoordinates))
        letters.append(currentCharacter);

      asciiMap.visitCoordinates(travelCoordinates);
      travelPath = Path.findPath(asciiMap, travelCoordinates, travelPath);
    }
    return new Solution(pathAsCharacters.toString(), letters.toString());
  }

  private Coordinates travelByPath(Coordinates coordinates, Path currentPath)
    throws Exception
  {
    switch (currentPath) {
      case UP:
        return coordinates.up();
      case DOWN:
        return coordinates.down();
      case LEFT:
        return coordinates.left();
      case RIGHT:
        return coordinates.right();
    }
    throw new Exception("No path found!");
  }
}
