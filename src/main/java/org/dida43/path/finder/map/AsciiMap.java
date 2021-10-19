package org.dida43.path.finder.map;

public class AsciiMap {
  private final char map[][];
  private final boolean mapOfCoordinatesVisited[][];

  private AsciiMap(char[][] map, boolean[][] mapOfCoordinatesVisited) {
    this.map = map;
    this.mapOfCoordinatesVisited = mapOfCoordinatesVisited;
  }

  public char[][] map() {
    return map;
  }

  public boolean[][] mapOfCoordinatesVisited() {
    return mapOfCoordinatesVisited;
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
