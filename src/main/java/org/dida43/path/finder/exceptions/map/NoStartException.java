package org.dida43.path.finder.exceptions.map;

public class NoStartException extends CheckMapException {
  public NoStartException() {
    super("No start found in ascii map");
  }
}
