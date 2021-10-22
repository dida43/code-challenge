package org.dida43.path.finder.enums;

public enum NonPathCharacters {
  SPACE(' '), OUT_OF_BOUNDS('\u0000');

  public final char value;

  NonPathCharacters(char value) {
    this.value = value;
  }

  public char value() {
    return value;
  }
}
