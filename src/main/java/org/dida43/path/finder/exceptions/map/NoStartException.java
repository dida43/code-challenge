package org.dida43.path.finder.exceptions.map;

public class NoStartException extends Exception {
  public NoStartException() {
    super("No start found in ascii map");
  }
}
