package org.dida43.path.finder.pojos;

import java.util.Objects;

public class Coordinates {
  private final int row;
  private final int column;

  public Coordinates(int row, int column) {
    this.row = row;
    this.column = column;
  }

  public int row() {
    return row;
  }

  public int column() {
    return column;
  }

  public Coordinates up() {
    return new Coordinates(row-1, column);
  }

  public Coordinates down() {
    return new Coordinates(row+1, column);
  }

  public Coordinates left() {
    return new Coordinates(row, column-1);
  }

  public Coordinates right() {
    return new Coordinates(row, column+1);
  }

  @Override public boolean equals(Object o) {
    if (this == o)
      return true;
    if (!(o instanceof Coordinates))
      return false;
    Coordinates that = (Coordinates)o;
    return row == that.row && column == that.column;
  }

  @Override public int hashCode() {
    return Objects.hash(row, column);
  }

  @Override public String toString() {
    return "{"+"row="+row+", column="+column+'}';
  }
}
