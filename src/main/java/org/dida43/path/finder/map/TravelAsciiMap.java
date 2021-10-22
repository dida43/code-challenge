package org.dida43.path.finder.map;

import org.dida43.path.finder.enums.Letters;
import org.dida43.path.finder.enums.Path;
import org.dida43.path.finder.exceptions.CheckMapException;
import org.dida43.path.finder.exceptions.PathException;
import org.dida43.path.finder.exceptions.path.BrokenPathException;
import org.dida43.path.finder.pojos.Position;
import org.dida43.path.finder.pojos.Solution;

public class TravelAsciiMap {

  private final AsciiMap asciiMap;

  public TravelAsciiMap(AsciiMap asciiMap) {
    this.asciiMap = asciiMap;
  }

  public Solution followPath() throws CheckMapException, PathException
  {
    StringBuilder pathAsCharacters = new StringBuilder();
    StringBuilder letters = new StringBuilder();

    Position travelPosition = asciiMap.startingPosition();
    Position endTravelPosition = asciiMap.endPosition();

    pathAsCharacters.append(asciiMap.getCharForPosition(travelPosition));

    asciiMap.visitPosition(travelPosition);
    Path travelPath = Path.findStartingPath(asciiMap, travelPosition);

    while (!travelPosition.equals(endTravelPosition)) {
      travelPosition = travelByPath(travelPosition, travelPath);
      char currentCharacter = asciiMap.getCharForPosition(travelPosition);

      if (!Path.charTraversable(currentCharacter))
        throw new BrokenPathException(travelPosition);

      pathAsCharacters.append(currentCharacter);
      if (Letters.isLetter(currentCharacter) && !asciiMap.isPositionVisited(travelPosition))
        letters.append(currentCharacter);

      asciiMap.visitPosition(travelPosition);
      travelPath = Path.findPath(asciiMap, travelPosition, travelPath);
    }
    return new Solution(pathAsCharacters.toString(), letters.toString());
  }

  private Position travelByPath(Position position, Path currentPath)
  {
    //todo check this exception
    switch (currentPath) {
      case UP:
        return position.up();
      case DOWN:
        return position.down();
      case LEFT:
        return position.left();
      case RIGHT:
        return position.right();
    }
    return null;
  }
}
