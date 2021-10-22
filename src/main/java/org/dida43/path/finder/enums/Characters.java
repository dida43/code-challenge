package org.dida43.path.finder.enums;

import java.util.HashMap;
import java.util.Map;

public enum Characters {
  START('@'), TURN('+'), HORIZONTAL('-'), VERTICAL('|'), END('x');

  public final char value;

  Characters(char value) {
    this.value = value;
  }

  public char value() {
    return value;
  }

  public static boolean isCharacter(char c) {
    return lookup.get(c) != null;
  }

  private static final Map<Character, Characters> lookup = new HashMap<>();

  static {
    for (Characters character : Characters.values()) {
      lookup.put(character.value, character);
    }
  }
}
