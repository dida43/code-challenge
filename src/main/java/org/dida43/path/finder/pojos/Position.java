package org.dida43.path.finder.pojos;

import java.util.Objects;

public class Position {
  private final int row;
  private final int column;

  public Position(int row, int column) {
    this.row = row;
    this.column = column;
  }

  public int row() {
    return row;
  }

  public int column() {
    return column;
  }

  public Position up() {
    return new Position(row-1, column);
  }

  public Position down() {
    return new Position(row+1, column);
  }

  public Position left() {
    return new Position(row, column-1);
  }

  public Position right() {
    return new Position(row, column+1);
  }

  @Override public boolean equals(Object o) {
    if (this == o)
      return true;
    if (!(o instanceof Position))
      return false;
    Position that = (Position)o;
    return row == that.row && column == that.column;
  }

  @Override public int hashCode() {
    return Objects.hash(row, column);
  }

  @Override public String toString() {
    return "{"+"row="+row+", column="+column+'}';
  }
}
