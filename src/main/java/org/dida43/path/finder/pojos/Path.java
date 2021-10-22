package org.dida43.path.finder.pojos;

public class Path {
  private final String pathAsCharacters;
  private final String pathAsLetters;

  public Path(String pathAsCharacters, String pathAsLetters) {
    this.pathAsCharacters = pathAsCharacters;
    this.pathAsLetters = pathAsLetters;
  }

  public String pathAsCharacters() {
    return pathAsCharacters;
  }

  public String pathAsLetters() {
    return pathAsLetters;
  }
}
