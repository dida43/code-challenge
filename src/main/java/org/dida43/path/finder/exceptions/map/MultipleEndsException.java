package org.dida43.path.finder.exceptions.map;

import org.dida43.path.finder.exceptions.CheckMapException;

public class MultipleEndsException extends CheckMapException {
  public MultipleEndsException() {
    super("Multiple ends found in ascii map");
  }
}
