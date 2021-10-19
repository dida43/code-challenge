package org.dida43.path.finder.enums;

public enum Characters {
  STARTING('@'), TURN('+'), HORIZONTAL('-'), VERTICAL('|'), END('x');

  public final char value;

  Characters(char value) {
    this.value = value;
  }

  public char value() {
    return value;
  }

  public static boolean isCharacter(char c) {
    for (Characters character : Characters.values()) {
      if (character.value() == c)
        return true;
    }
    return false;
  }
}
