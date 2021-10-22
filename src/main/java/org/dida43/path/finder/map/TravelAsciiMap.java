package org.dida43.path.finder.map;

import org.dida43.path.finder.enums.Letters;
import org.dida43.path.finder.enums.PathDirection;
import org.dida43.path.finder.exceptions.CheckMapException;
import org.dida43.path.finder.exceptions.PathDirectionException;
import org.dida43.path.finder.exceptions.map.NextPositionOnPathException;
import org.dida43.path.finder.exceptions.path.BrokenPathDirectionException;
import org.dida43.path.finder.pojos.Position;
import org.dida43.path.finder.pojos.Solution;

public class TravelAsciiMap {

  private final AsciiMap asciiMap;

  public TravelAsciiMap(AsciiMap asciiMap) {
    this.asciiMap = asciiMap;
  }

  public Solution followPathToSolution() throws CheckMapException, PathDirectionException
  {
    StringBuilder pathAsCharacters = new StringBuilder();
    StringBuilder letters = new StringBuilder();

    Position positionOnPath = asciiMap.startingPosition();
    Position endPosition = asciiMap.endPosition();

    pathAsCharacters.append(asciiMap.getCharForPosition(positionOnPath));

    asciiMap.visitPosition(positionOnPath);
    PathDirection pathDirection = PathDirection.getStartingPathDirection(asciiMap, positionOnPath);

    while (!positionOnPath.equals(endPosition)) {
      positionOnPath = nextPositionOnPath(positionOnPath, pathDirection);
      char currentCharacter = asciiMap.getCharForPosition(positionOnPath);

      if (PathDirection.isPathBroken(currentCharacter))
        throw new BrokenPathDirectionException(positionOnPath);

      pathAsCharacters.append(currentCharacter);
      if (Letters.isLetter(currentCharacter) && !asciiMap.isPositionVisited(positionOnPath))
        letters.append(currentCharacter);

      asciiMap.visitPosition(positionOnPath);
      pathDirection = PathDirection.getPathDirection(asciiMap, positionOnPath, pathDirection);
    }
    return new Solution(pathAsCharacters.toString(), letters.toString());
  }

  private Position nextPositionOnPath(Position position, PathDirection pathDirection)
    throws NextPositionOnPathException
  {
    switch (pathDirection) {
      case UP:
        return position.up();
      case DOWN:
        return position.down();
      case LEFT:
        return position.left();
      case RIGHT:
        return position.right();
    }
    throw new NextPositionOnPathException(position, pathDirection);
  }
}
