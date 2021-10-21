package org.dida43.path.finder.exceptions.path;

import org.dida43.path.finder.exceptions.PathException;
import org.dida43.path.finder.pojos.Coordinates;

public class NoStartingPathException extends PathException {
  public NoStartingPathException(Coordinates coordinates) {
    super("No starting path from coordinates: "+coordinates.toString());
  }
}
