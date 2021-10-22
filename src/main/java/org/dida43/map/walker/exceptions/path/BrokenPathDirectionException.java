package org.dida43.map.walker.exceptions.path;

import org.dida43.map.walker.exceptions.PathDirectionException;
import org.dida43.map.walker.pojos.Position;

public class BrokenPathDirectionException extends PathDirectionException {
  public BrokenPathDirectionException(Position position) {
    super("Broken path on position: "+position.toString());
  }
}
