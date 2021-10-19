package org.dida43.path.finder.enums;

public enum Letters {
  A, B, C, D, E, G, H, I, L, N, O, S;

  public static boolean isLetter(char c) {
    for (Letters letter : Letters.values()) {
      if (letter.name().equals(String.valueOf(c)))
        return true;
    }
    return false;
  }
}
