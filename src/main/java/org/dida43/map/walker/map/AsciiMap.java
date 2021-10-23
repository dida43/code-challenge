package org.dida43.map.walker.map;

import org.dida43.map.walker.enums.LetterCharacters;
import org.dida43.map.walker.enums.NonPathCharacters;
import org.dida43.map.walker.enums.PathCharacters;
import org.dida43.map.walker.exceptions.AsciiMapException;
import org.dida43.map.walker.exceptions.map.*;
import org.dida43.map.walker.pojos.Position;

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
      return NonPathCharacters.OUT_OF_BOUNDS.value();
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
        if (map[i][j] == NonPathCharacters.START.value()) {
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
        if (map[i][j] == PathCharacters.END.value()) {
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

  public static AsciiMap ofString(String string) throws AsciiMapException {
    if (string.isEmpty())
      throw new AsciiMapException("Cannot make ascii map for empty string");
    if (!isStringValid(string))
      throw new AsciiMapException("Cannot make ascii map for non valid characters");

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
    String regex =
      "["+LetterCharacters.charset+PathCharacters.charset+NonPathCharacters.SPACE.value()+
      NonPathCharacters.START.value()+"]+";
    return withoutNewLine.matches(regex);
  }
}
