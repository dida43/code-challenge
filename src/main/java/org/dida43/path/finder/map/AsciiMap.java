package org.dida43.path.finder.map;

import org.dida43.path.finder.enums.Characters;
import org.dida43.path.finder.enums.Direction;
import org.dida43.path.finder.enums.Letters;
import org.dida43.path.finder.pojos.Coordinates;

public class AsciiMap {
  private final char map[][];
  private final boolean mapOfCoordinatesVisited[][];

  public AsciiMap(String inputFile) {
    String[] rowLines = inputFile.split(System.lineSeparator());
    int rows = rowLines.length;
    int columns = rowLines[0].length();
    mapOfCoordinatesVisited = new boolean[rows][];
    map = new char[rows][columns];
    for (int i = 0; i < rows; i++) {
      map[i] = rowLines[i].toCharArray();
      mapOfCoordinatesVisited[i] = new boolean[map[i].length];
    }
  }

  private Coordinates getStartCoordinates() throws Exception {
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

  private Coordinates getEndCoordinates() throws Exception {
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

  public Solution findSolution() throws Exception {
    Solution solution = new Solution();

    Coordinates currentCoordinates = getStartCoordinates();
    Coordinates endCoordinates = getEndCoordinates();

    updateMapOfCoordinatesVisited(currentCoordinates);

    solution.appendPathCharacter(getCharForCoordinates(currentCoordinates));
    Direction currentDirection = findStartingDirection(currentCoordinates);

    while (!currentCoordinates.equals(endCoordinates)) {
      currentCoordinates = updateCoordinates(currentCoordinates, currentDirection);
      solution.appendPathCharacter(getCharForCoordinates(currentCoordinates));
      solution.appendLetter(getCharForCoordinates(currentCoordinates), areCoordinatesVisited(currentCoordinates));
      updateMapOfCoordinatesVisited(currentCoordinates);
      currentDirection = findDirection(currentCoordinates, currentDirection);
    }
    return solution;
  }

  private void updateMapOfCoordinatesVisited(Coordinates coordinates) {
    mapOfCoordinatesVisited[coordinates.row()][coordinates.column()] = true;
  }

  private boolean areCoordinatesVisited(Coordinates position) {
    return mapOfCoordinatesVisited[position.row()][position.column()];
  }

  private Direction findDirection(Coordinates position, Direction currentDirection) throws Exception
  {
    char inspected = getCharForCoordinates(position);
    if (inspected == Characters.TURN.value())
      return handleTurn(position, currentDirection);
    if (Letters.isLetter(inspected))
      return handleLetter(position, currentDirection);
    return currentDirection;
  }

  private Direction handleTurn(Coordinates coordinates, Direction currentDirection) throws Exception {
    if (currentDirection == Direction.UP || currentDirection == Direction.DOWN) {
      char leftChar = getCharForCoordinates(coordinates.left());
      char rightChar = getCharForCoordinates(coordinates.right());
      if (charTraversable(leftChar) && charTraversable(rightChar))
        throw new Exception("T intersection error");
      if (charTraversable(leftChar))
        return Direction.LEFT;
      if (charTraversable(rightChar))
        return Direction.RIGHT;
    }
    if (currentDirection == Direction.LEFT || currentDirection == Direction.RIGHT) {
      char upChar = getCharForCoordinates(coordinates.up());
      char downChar = getCharForCoordinates(coordinates.down());
      if (charTraversable(upChar) && charTraversable(downChar))
        throw new Exception("T intersection error");
      if (charTraversable(upChar))
        return Direction.UP;
      if (charTraversable(downChar))
        return Direction.DOWN;
    }
    throw new Exception("Fake turn");
  }

  private Direction handleLetter(Coordinates coordinates, Direction currentDirection) throws Exception
  {
    char upChar = getCharForCoordinates(coordinates.up());
    char downChar = getCharForCoordinates(coordinates.down());
    char leftChar = getCharForCoordinates(coordinates.left());
    char rightChar = getCharForCoordinates(coordinates.right());
    if (currentDirection == Direction.UP && charTraversable(upChar))
      return currentDirection;
    if (currentDirection == Direction.DOWN && charTraversable(downChar))
      return currentDirection;
    if (currentDirection == Direction.LEFT && charTraversable(leftChar))
      return currentDirection;
    if (currentDirection == Direction.RIGHT && charTraversable(rightChar))
      return currentDirection;

    return handleTurn(coordinates, currentDirection);
  }

  private boolean charTraversable(char c) {
    return Letters.isLetter(c) || Characters.isCharacter(c);
  }

  private Coordinates updateCoordinates(Coordinates coordinates, Direction currentDirection)
    throws Exception
  {
    switch (currentDirection) {
      case UP:
        return coordinates.up();
      case DOWN:
        return coordinates.down();
      case LEFT:
        return coordinates.left();
      case RIGHT:
        return coordinates.right();
    }
    throw new Exception("No direction found!");
  }

  private Direction findStartingDirection(Coordinates coordinates) throws Exception {
    Direction startingDirection = null;
    int noOfTraversableChars = 0;
    if(charTraversable(getCharForCoordinates(coordinates.up()))){
      noOfTraversableChars++;
      startingDirection = Direction.UP;
    }
    if(charTraversable(getCharForCoordinates(coordinates.down()))){
      noOfTraversableChars++;
      startingDirection = Direction.DOWN;
    }
    if(charTraversable(getCharForCoordinates(coordinates.left()))){
      noOfTraversableChars++;
      startingDirection = Direction.LEFT;
    }
    if(charTraversable(getCharForCoordinates(coordinates.right()))){
      noOfTraversableChars++;
      startingDirection = Direction.RIGHT;
    }

    if(noOfTraversableChars != 1)
      throw new Exception("Multiple or no starting paths");

    return startingDirection;
  }

  private char getCharForCoordinates(Coordinates coordinates) {
    try {
      return map[coordinates.row()][coordinates.column()];
    } catch (ArrayIndexOutOfBoundsException ex) {
      return 0;
    }
  }
}
