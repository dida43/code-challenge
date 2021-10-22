package org.dida43.path.finder.exceptions.path;

import org.dida43.path.finder.exceptions.PathException;
import org.dida43.path.finder.pojos.Position;

public class TForkPathException extends PathException {
  public TForkPathException(Position position) {
    super("Position: "+position.toString()+" contain T fork. Cannot determine path");
  }
}
