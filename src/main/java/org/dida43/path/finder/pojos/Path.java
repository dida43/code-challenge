package org.dida43.path.finder.pojos;

public class Path {
  private final String asCharacters;
  private final String asLetters;

  public Path(String asCharacters, String asLetters) {
    this.asCharacters = asCharacters;
    this.asLetters = asLetters;
  }

  public String asCharacters() {
    return asCharacters;
  }

  public String asLetters() {
    return asLetters;
  }
}
