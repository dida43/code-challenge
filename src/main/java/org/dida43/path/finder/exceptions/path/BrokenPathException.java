package org.dida43.path.finder.exceptions.path;

import org.dida43.path.finder.exceptions.PathException;
import org.dida43.path.finder.pojos.Coordinates;

public class BrokenPathException extends PathException {
  public BrokenPathException(Coordinates coordinates) {
    super("Broken path on coordinates: "+coordinates.toString());
  }
}
