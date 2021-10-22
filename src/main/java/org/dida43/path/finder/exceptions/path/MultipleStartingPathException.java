package org.dida43.path.finder.exceptions.path;

import org.dida43.path.finder.exceptions.PathException;
import org.dida43.path.finder.pojos.Position;

public class MultipleStartingPathException extends PathException {
  public MultipleStartingPathException(Position position) {
    super("Multiple paths from position: "+position.toString()+" instead of one.");
  }
}
