package org.dida43.path.finder.exceptions.path;

import org.dida43.path.finder.exceptions.PathDirectionException;
import org.dida43.path.finder.pojos.Position;

public class BrokenPathDirectionException extends PathDirectionException {
  public BrokenPathDirectionException(Position position) {
    super("Broken path on position: "+position.toString());
  }
}
