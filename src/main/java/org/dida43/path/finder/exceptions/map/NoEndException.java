package org.dida43.path.finder.exceptions.map;

import org.dida43.path.finder.exceptions.CheckMapException;

public class NoEndException extends CheckMapException {
  public NoEndException() {
    super("No end found in ascii map");
  }
}
