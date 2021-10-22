package org.dida43.map.walker.exceptions.map;

import org.dida43.map.walker.exceptions.CheckMapException;

public class NoStartException extends CheckMapException {
  public NoStartException() {
    super("No start found in ascii map");
  }
}
