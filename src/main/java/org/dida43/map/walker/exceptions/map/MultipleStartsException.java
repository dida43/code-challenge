package org.dida43.map.walker.exceptions.map;

import org.dida43.map.walker.exceptions.CheckMapException;

public class MultipleStartsException extends CheckMapException {
  public MultipleStartsException() {
    super("Multiple starts found in ascii map");
  }
}
