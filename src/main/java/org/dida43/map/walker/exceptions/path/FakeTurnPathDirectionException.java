package org.dida43.map.walker.exceptions.path;

import org.dida43.map.walker.exceptions.PathDirectionException;
import org.dida43.map.walker.pojos.Position;

public class FakeTurnPathDirectionException extends PathDirectionException {
  public FakeTurnPathDirectionException(Position position) {
    super("Position: "+position.toString()+" contain fake turn.");
  }
}
