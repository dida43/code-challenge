package org.dida43.path.finder.exceptions.path;

import org.dida43.path.finder.exceptions.PathDirectionException;
import org.dida43.path.finder.pojos.Position;

public class FakeTurnPathDirectionException extends PathDirectionException {
  public FakeTurnPathDirectionException(Position position) {
    super("Position: "+position.toString()+" contain fake turn.");
  }
}
