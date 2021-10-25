package org.dida43.map.walker.characters;

public enum NonPathCharacters {
  START('@'), END('x'), SPACE(' '), OUT_OF_BOUNDS('\u0000');

  private final char value;

  NonPathCharacters(char value) {
    this.value = value;
  }

  public char value() {
    return value;
  }
}
