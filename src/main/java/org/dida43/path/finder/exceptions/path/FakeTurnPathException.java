package org.dida43.path.finder.exceptions.path;

import org.dida43.path.finder.exceptions.PathException;
import org.dida43.path.finder.pojos.Position;

public class FakeTurnPathException extends PathException {
  public FakeTurnPathException(Position position) {
    super("Position: "+position.toString()+" contain fake turn.");
  }
}
