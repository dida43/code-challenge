package org.dida43.map.walker.characters;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public enum Characters {
  A('A'), B('B'), C('C'), D('D'), E('E'), G('G'), H('H'), I('I'), L('L'), N('N'), O('O'), S('S'),
  TURN('+'), HORIZONTAL('-'), VERTICAL('|'), END('x'), START('@'), SPACE(' '), NON_VALID('\u0000');

  private final char value;

  private static final Map<Character, Characters> lookup = new HashMap<>();

  static {
    for (Characters character : Characters.values()) {
      lookup.put(character.value(), character);
    }
  }

  private static final Set<Characters> charset =
    EnumSet.complementOf(EnumSet.of(Characters.NON_VALID));

  private static final Set<Characters> letters = EnumSet.range(Characters.A, Characters.S);

  private static final Set<Characters> path = EnumSet.range(Characters.A, Characters.END);

  Characters(char value) {
    this.value = value;
  }

  public char value() {
    return value;
  }

  public static String charset() {
    StringBuilder asciiMapCharset = new StringBuilder();
    charset.forEach(character -> asciiMapCharset.append(character.value));
    return asciiMapCharset.toString();
  }

  public static boolean isPath(char c) {
    return path.contains(Characters.valueOf(c));
  }

  public static boolean isLetter(char c) {
    return letters.contains(Characters.valueOf(c));
  }

  private static Characters valueOf(char c) {
    if (lookup.containsKey(c))
      return lookup.get(c);
    return Characters.NON_VALID;
  }
}
