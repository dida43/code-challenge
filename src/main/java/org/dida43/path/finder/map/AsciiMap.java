package org.dida43.path.finder.map;

import org.dida43.path.finder.enums.Characters;
import org.dida43.path.finder.exceptions.map.*;
import org.dida43.path.finder.pojos.Coordinates;

public class AsciiMap {
  private final char map[][];
  private final boolean mapOfCoordinatesVisited[][];

  private AsciiMap(char[][] map, boolean[][] mapOfCoordinatesVisited) {
    this.map = map;
    this.mapOfCoordinatesVisited = mapOfCoordinatesVisited;
  }

  public char getCharForCoordinates(Coordinates coordinates) {
    try {
      return map[coordinates.row()][coordinates.column()];
    } catch (ArrayIndexOutOfBoundsException ex) {
      return 0;
    }
  }

  public void visitCoordinates(Coordinates coordinates) throws VisitCoordinatesException {
    try {
      mapOfCoordinatesVisited[coordinates.row()][coordinates.column()] = true;
    } catch (ArrayIndexOutOfBoundsException ex) {
      throw new VisitCoordinatesException(coordinates);
    }
  }

  public boolean areCoordinatesVisited(Coordinates coordinates) throws AreCoordinatesVisitedException {
    try {
      return mapOfCoordinatesVisited[coordinates.row()][coordinates.column()];
    } catch (ArrayIndexOutOfBoundsException ex) {
      throw new AreCoordinatesVisitedException(coordinates);
    }
  }

  public Coordinates startCoordinates() throws MultipleStartsException, NoStartException {
    Coordinates startingCoordinates = null;
    for (int i = 0; i < map.length; i++) {
      for (int j = 0; j < map[i].length; j++) {
        if (map[i][j] == Characters.STARTING.value) {
          if (startingCoordinates != null)
            throw new MultipleStartsException();
          startingCoordinates = new Coordinates(i, j);
        }
      }
    }
    if (startingCoordinates == null)
      throw new NoStartException();
    return startingCoordinates;
  }

  public Coordinates endCoordinates() throws MultipleEndsException, NoEndException {
    Coordinates endCoordinates = null;
    for (int i = 0; i < map.length; i++) {
      for (int j = 0; j < map[i].length; j++) {
        if (map[i][j] == Characters.END.value) {
          if (endCoordinates != null)
            throw new MultipleEndsException();
          endCoordinates = new Coordinates(i, j);
        }
      }
    }
    if (endCoordinates == null)
      throw new NoEndException();
    return endCoordinates;
  }

  public static AsciiMap ofString(String inputFile) {
    //todo should we have exception here?
    String[] rowLines = inputFile.split(System.lineSeparator());
    int rows = rowLines.length;
    char[][] map = new char[rows][];
    boolean[][] mapOfCoordinatesVisited = new boolean[rows][];
    for (int i = 0; i < rows; i++) {
      map[i] = rowLines[i].toCharArray();
      mapOfCoordinatesVisited[i] = new boolean[map[i].length];
    }
    return new AsciiMap(map, mapOfCoordinatesVisited);
  }
}
