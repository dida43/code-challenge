package org.dida43.path.finder.exceptions.path;

import org.dida43.path.finder.exceptions.PathDirectionException;
import org.dida43.path.finder.pojos.Position;

public class MultipleStartingPathDirectionException extends PathDirectionException {
  public MultipleStartingPathDirectionException(Position position) {
    super("Multiple paths from position: "+position.toString()+" instead of one.");
  }
}
