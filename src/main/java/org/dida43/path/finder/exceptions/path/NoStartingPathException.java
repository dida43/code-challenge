package org.dida43.path.finder.exceptions.path;

import org.dida43.path.finder.exceptions.PathException;
import org.dida43.path.finder.pojos.Position;

public class NoStartingPathException extends PathException {
  public NoStartingPathException(Position position) {
    super("No starting path from position: "+position.toString());
  }
}
