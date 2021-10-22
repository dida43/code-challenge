package org.dida43.path.finder.exceptions.path;

import org.dida43.path.finder.exceptions.PathException;
import org.dida43.path.finder.pojos.Position;

public class BrokenPathException extends PathException {
  public BrokenPathException(Position position) {
    super("Broken path on position: "+position.toString());
  }
}
