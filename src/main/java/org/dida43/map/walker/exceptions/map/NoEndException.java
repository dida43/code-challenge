package org.dida43.map.walker.exceptions.map;

import org.dida43.map.walker.exceptions.CheckMapException;

public class NoEndException extends CheckMapException {
  public NoEndException() {
    super("No end found in ascii map");
  }
}
