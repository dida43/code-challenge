package org.dida43.path.finder.exceptions.map;

import org.dida43.path.finder.pojos.Coordinates;

public class MultipleStartsException extends CheckMapException {
  public MultipleStartsException() {
    super("Multiple starts found in ascii map");
  }
}
