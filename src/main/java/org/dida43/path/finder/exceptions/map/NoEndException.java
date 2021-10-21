package org.dida43.path.finder.exceptions.map;

public class NoEndException extends Exception {
  public NoEndException() {
    super("No end found in ascii map");
  }
}
