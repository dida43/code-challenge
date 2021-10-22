package org.dida43.map.walker.exceptions.path;

import org.dida43.map.walker.exceptions.PathDirectionException;
import org.dida43.map.walker.pojos.Position;

public class NoStartingPathDirectionException extends PathDirectionException {
  public NoStartingPathDirectionException(Position position) {
    super("No starting path from position: "+position.toString());
  }
}
