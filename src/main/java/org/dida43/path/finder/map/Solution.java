package org.dida43.path.finder.map;

import org.dida43.path.finder.enums.Letters;

public class Solution {
  private final StringBuilder pathAsCharacters = new StringBuilder();
  private final StringBuilder letters = new StringBuilder();

  public void appendPathCharacter(char pathCharacter) {
    pathAsCharacters.append(pathCharacter);
  }

  public void appendLetter(char pathCharacter, boolean visited) {
    if (Letters.isLetter(pathCharacter) && !visited)
      letters.append(pathCharacter);
  }

  public String pathAsCharacters() {
    return pathAsCharacters.toString();
  }

  public String letters() {
    return letters.toString();
  }
}
