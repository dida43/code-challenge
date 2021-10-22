package org.dida43.map.walker.exceptions.map;

import org.dida43.map.walker.exceptions.CheckMapException;

public class MultipleEndsException extends CheckMapException {
  public MultipleEndsException() {
    super("Multiple ends found in ascii map");
  }
}
