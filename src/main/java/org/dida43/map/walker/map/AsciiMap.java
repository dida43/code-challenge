package org.dida43.map.walker.map;

import org.dida43.map.walker.CodeChallengeException;
import org.dida43.map.walker.characters.Characters;
import org.dida43.map.walker.path.Position;

public class AsciiMap {
  private final char map[][];
  private final boolean mapOfPositionsVisited[][];

  private AsciiMap(char[][] map, boolean[][] mapOfPositionsVisited) {
    this.map = map;
    this.mapOfPositionsVisited = mapOfPositionsVisited;
  }

  public char getCharForPosition(Position position) {
    try {
      return map[position.row()][position.column()];
    } catch (ArrayIndexOutOfBoundsException ex) {
      return Characters.NON_VALID.value();
    }
  }

  public void visitPosition(Position position) throws VisitPositionException {
    try {
      mapOfPositionsVisited[position.row()][position.column()] = true;
    } catch (ArrayIndexOutOfBoundsException ex) {
      throw new VisitPositionException(position);
    }
  }

  public boolean isPositionVisited(Position position) throws IsPositionVisitedException {
    try {
      return mapOfPositionsVisited[position.row()][position.column()];
    } catch (ArrayIndexOutOfBoundsException ex) {
      throw new IsPositionVisitedException(position);
    }
  }

  public Position startPosition() throws MultipleStartsException, NoStartException {
    Position startingPosition = null;
    for (int i = 0; i < map.length; i++) {
      for (int j = 0; j < map[i].length; j++) {
        if (map[i][j] == Characters.START.value()) {
          if (startingPosition != null)
            throw new MultipleStartsException();
          startingPosition = new Position(i, j);
        }
      }
    }
    if (startingPosition == null)
      throw new NoStartException();
    return startingPosition;
  }

  public Position endPosition() throws MultipleEndsException, NoEndException {
    Position endPosition = null;
    for (int i = 0; i < map.length; i++) {
      for (int j = 0; j < map[i].length; j++) {
        if (map[i][j] == Characters.END.value()) {
          if (endPosition != null)
            throw new MultipleEndsException();
          endPosition = new Position(i, j);
        }
      }
    }
    if (endPosition == null)
      throw new NoEndException();
    return endPosition;
  }

  public static AsciiMap ofString(String string) throws CodeChallengeException {
    if (string == null)
      throw new CodeChallengeException("Cannot make ascii map for non existing string");
    if (string.isEmpty())
      throw new CodeChallengeException("Cannot make ascii map for empty string");
    if (!isStringValid(string))
      throw new CodeChallengeException("Cannot make ascii map for non valid characters");

    String[] rows = string.split(System.lineSeparator());
    char[][] map = new char[rows.length][];
    boolean[][] mapOfPositionsVisited = new boolean[rows.length][];
    for (int i = 0; i < rows.length; i++) {
      map[i] = rows[i].toCharArray();
      mapOfPositionsVisited[i] = new boolean[map[i].length];
    }
    return new AsciiMap(map, mapOfPositionsVisited);
  }

  private static boolean isStringValid(String s) {
    String withoutNewLine = s.replace(System.lineSeparator(), "");
    String regex = "["+Characters.charset()+"]+";
    return withoutNewLine.matches(regex);
  }

  protected static class MultipleStartsException extends CodeChallengeException {
    public MultipleStartsException() {
      super("Multiple starts found in ascii map");
    }
  }

  protected static class MultipleEndsException extends CodeChallengeException {
    public MultipleEndsException() {
      super("Multiple ends found in ascii map");
    }
  }

  protected static class NoStartException extends CodeChallengeException {
    public NoStartException() {
      super("No start found in ascii map");
    }
  }

  protected static class NoEndException extends CodeChallengeException {
    public NoEndException() {
      super("No end found in ascii map");
    }
  }

  protected static class VisitPositionException extends CodeChallengeException {
    public VisitPositionException(Position position) {
      super("Cannot visit position: "+position.toString()+" because it is out of map.");
    }
  }

  protected static class IsPositionVisitedException extends CodeChallengeException {
    public IsPositionVisitedException(Position position) {
      super(
        "Cannot check if position: "+position.toString()+" is visited because it is out of map.");
    }
  }
}
