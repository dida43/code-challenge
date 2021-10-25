package org.dida43.map.walker.enums;

import java.util.HashMap;
import java.util.Map;

public enum PathCharacters {
  TURN('+'), HORIZONTAL('-'), VERTICAL('|');

  private final char value;

  public static final String charset;

  PathCharacters(char value) {
    this.value = value;
  }

  public char value() {
    return value;
  }

  public static boolean isPathCharacter(char c) {
    return lookup.get(c) != null;
  }

  private static final Map<Character, PathCharacters> lookup = new HashMap<>();

  static {
    StringBuilder charsetBuilder = new StringBuilder();
    for (PathCharacters character : PathCharacters.values()) {
      lookup.put(character.value, character);
      charsetBuilder.append(character.value);
    }
    charset = charsetBuilder.toString();
  }
}
