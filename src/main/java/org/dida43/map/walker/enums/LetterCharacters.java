package org.dida43.map.walker.enums;

import java.util.HashMap;
import java.util.Map;

public enum LetterCharacters {
  A, B, C, D, E, G, H, I, L, N, O, S;

  public static final String charset;

  public static boolean isLetter(char c) {
    return lookup.get(c) != null;
  }

  private static final Map<Character, LetterCharacters> lookup = new HashMap<>();

  static {
    StringBuilder charsetBuilder = new StringBuilder();
    for (LetterCharacters letter : LetterCharacters.values()) {
      lookup.put(letter.name().charAt(0), letter);
      charsetBuilder.append(letter.name().charAt(0));
    }
    charset = charsetBuilder.toString();
  }
}
