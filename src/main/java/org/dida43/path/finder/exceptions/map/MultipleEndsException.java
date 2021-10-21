package org.dida43.path.finder.exceptions.map;

public class MultipleEndsException extends Exception {
  public MultipleEndsException() {
    super("Multiple ends found in ascii map");
  }
}
