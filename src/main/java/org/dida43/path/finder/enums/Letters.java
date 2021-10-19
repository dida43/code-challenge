package org.dida43.path.finder.enums;

import java.util.HashMap;
import java.util.Map;

public enum Letters {
  A, B, C, D, E, G, H, I, L, N, O, S;

  public static boolean isLetter(char c) {
    return lookup.get(c) != null;
  }

  private static final Map<Character, Letters> lookup = new HashMap<>();

  static {
    for (Letters letter : Letters.values()) {
      lookup.put(letter.name().charAt(0), letter);
    }
  }
}
