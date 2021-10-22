package org.dida43.path.finder.map;

import org.dida43.path.finder.enums.Characters;
import org.dida43.path.finder.exceptions.map.*;
import org.dida43.path.finder.pojos.Position;

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
      return 0;
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

  public Position startingPosition() throws MultipleStartsException, NoStartException {
    Position startingPosition = null;
    for (int i = 0; i < map.length; i++) {
      for (int j = 0; j < map[i].length; j++) {
        if (map[i][j] == Characters.STARTING.value) {
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
        if (map[i][j] == Characters.END.value) {
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

  public static AsciiMap ofString(String inputFile) {
    //todo should we have exception here?
    String[] rowLines = inputFile.split(System.lineSeparator());
    int rows = rowLines.length;
    char[][] map = new char[rows][];
    boolean[][] mapOfPositionsVisited = new boolean[rows][];
    for (int i = 0; i < rows; i++) {
      map[i] = rowLines[i].toCharArray();
      mapOfPositionsVisited[i] = new boolean[map[i].length];
    }
    return new AsciiMap(map, mapOfPositionsVisited);
  }
}
