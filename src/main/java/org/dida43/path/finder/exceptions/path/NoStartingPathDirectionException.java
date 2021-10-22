package org.dida43.path.finder.exceptions.path;

import org.dida43.path.finder.exceptions.PathDirectionException;
import org.dida43.path.finder.pojos.Position;

public class NoStartingPathDirectionException extends PathDirectionException {
  public NoStartingPathDirectionException(Position position) {
    super("No starting path from position: "+position.toString());
  }
}
