package org.dida43.map.walker.exceptions.path;

import org.dida43.map.walker.exceptions.PathDirectionException;
import org.dida43.map.walker.pojos.Position;

public class MultipleStartingPathDirectionException extends PathDirectionException {
  public MultipleStartingPathDirectionException(Position position) {
    super("Multiple paths from position: "+position.toString()+" instead of one.");
  }
}
