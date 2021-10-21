package org.dida43.path.finder.pojos;

public class Solution {
  private final String pathAsCharacters;
  private final String letters;

  public Solution(String pathAsCharacters, String letters) {
    this.pathAsCharacters = pathAsCharacters;
    this.letters = letters;
  }

  public String pathAsCharacters() {
    return pathAsCharacters;
  }

  public String letters() {
    return letters;
  }
}
