package org.dida43.map.walker.map;

import org.dida43.map.walker.enums.LetterCharacters;
import org.dida43.map.walker.enums.PathDirection;
import org.dida43.map.walker.exceptions.CheckMapException;
import org.dida43.map.walker.exceptions.PathDirectionException;
import org.dida43.map.walker.exceptions.map.NextPositionOnPathException;
import org.dida43.map.walker.exceptions.path.BrokenPathDirectionException;
import org.dida43.map.walker.pojos.Path;
import org.dida43.map.walker.pojos.Position;

public class AsciiMapWalker {

  public static Path recordPath(AsciiMap asciiMap) throws CheckMapException, PathDirectionException
  {
    StringBuilder pathAsCharacters = new StringBuilder();
    StringBuilder letters = new StringBuilder();

    Position positionOnPath = asciiMap.startPosition();
    Position endPosition = asciiMap.endPosition();

    pathAsCharacters.append(asciiMap.getCharForPosition(positionOnPath));

    asciiMap.visitPosition(positionOnPath);
    PathDirection pathDirection = PathDirection.getStartingPathDirection(asciiMap, positionOnPath);

    while (!positionOnPath.equals(endPosition)) {
      positionOnPath = nextPositionOnPath(positionOnPath, pathDirection);
      char currentCharacter = asciiMap.getCharForPosition(positionOnPath);

      //todo check this
      if (!PathDirection.canTravel(currentCharacter))
        throw new BrokenPathDirectionException(positionOnPath);

      pathAsCharacters.append(currentCharacter);
      if (LetterCharacters.isLetter(currentCharacter) &&
          !asciiMap.isPositionVisited(positionOnPath))
        letters.append(currentCharacter);

      asciiMap.visitPosition(positionOnPath);
      pathDirection = PathDirection.getPathDirection(asciiMap, positionOnPath, pathDirection);
    }
    return new Path(pathAsCharacters.toString(), letters.toString());
  }

  private static Position nextPositionOnPath(Position position, PathDirection pathDirection)
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
