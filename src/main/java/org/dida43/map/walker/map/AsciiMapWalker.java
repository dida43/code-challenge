package org.dida43.map.walker.map;

import org.dida43.map.walker.CodeChallengeException;
import org.dida43.map.walker.characters.LetterCharacters;
import org.dida43.map.walker.characters.NonPathCharacters;
import org.dida43.map.walker.characters.PathCharacters;
import org.dida43.map.walker.path.Path;
import org.dida43.map.walker.path.PathDirection;
import org.dida43.map.walker.path.Position;

public class AsciiMapWalker {

  public static Path recordPath(AsciiMap asciiMap) throws CodeChallengeException
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

      if (!canWalk(currentCharacter))
        throw new BrokenPathException(positionOnPath);

      pathAsCharacters.append(currentCharacter);
      if (LetterCharacters.isLetter(currentCharacter) &&
          !asciiMap.isPositionVisited(positionOnPath))
        letters.append(currentCharacter);

      asciiMap.visitPosition(positionOnPath);
      pathDirection = PathDirection.getPathDirection(asciiMap, positionOnPath, pathDirection);
    }
    return new Path(pathAsCharacters.toString(), letters.toString());
  }

  public static boolean canWalk(char c) {
    return LetterCharacters.isLetter(c) || PathCharacters.isPathCharacter(c) ||
           c == NonPathCharacters.END.value();
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

  public static class NextPositionOnPathException extends CodeChallengeException {
    public NextPositionOnPathException(Position position, PathDirection pathDirection) {
      super("Cannot get next position from: "+position.toString()+" for path direction: "+
            pathDirection.toString());
    }
  }

  public static class BrokenPathException extends CodeChallengeException {
    public BrokenPathException(Position position) {
      super("Broken path on position: "+position.toString());
    }
  }
}
