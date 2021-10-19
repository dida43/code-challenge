package org.dida43.path.finder.map;

import org.dida43.path.finder.enums.Characters;
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

  public void updateMapOfCoordinatesVisited(Coordinates coordinates) {
    mapOfCoordinatesVisited[coordinates.row()][coordinates.column()] = true;
  }

  public boolean areCoordinatesVisited(Coordinates position) {
    return mapOfCoordinatesVisited[position.row()][position.column()];
  }

  public Coordinates getStartCoordinates() throws Exception {
    Coordinates startingCoordinates = null;
    for (int i = 0; i < map.length; i++) {
      for (int j = 0; j < map[i].length; j++) {
        if (map[i][j] == Characters.STARTING.value) {
          if (startingCoordinates != null)
            throw new Exception("multiple starting coordinates");
          startingCoordinates = new Coordinates(i, j);
        }
      }
    }
    if (startingCoordinates == null)
      throw new Exception("no starting point");
    return startingCoordinates;
  }

  public Coordinates getEndCoordinates() throws Exception {
    Coordinates endCoordinates = null;
    for (int i = 0; i < map.length; i++) {
      for (int j = 0; j < map[i].length; j++) {
        if (map[i][j] == Characters.END.value) {
          if (endCoordinates != null)
            throw new Exception("multiple ending coordinates");
          endCoordinates = new Coordinates(i, j);
        }
      }
    }
    if (endCoordinates == null)
      throw new Exception("no ending coordinates");
    return endCoordinates;
  }

  public static AsciiMap ofString(String inputFile) {
    String[] rowLines = inputFile.split(System.lineSeparator());
    int rows = rowLines.length;
    int columns = rowLines[0].length();
    char[][] map = new char[rows][columns];
    boolean[][] mapOfCoordinatesVisited = new boolean[rows][];
    for (int i = 0; i < rows; i++) {
      map[i] = rowLines[i].toCharArray();
      mapOfCoordinatesVisited[i] = new boolean[map[i].length];
    }
    return new AsciiMap(map, mapOfCoordinatesVisited);
  }
}
