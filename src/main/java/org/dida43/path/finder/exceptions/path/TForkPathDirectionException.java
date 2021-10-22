package org.dida43.path.finder.exceptions.path;

import org.dida43.path.finder.exceptions.PathDirectionException;
import org.dida43.path.finder.pojos.Position;

public class TForkPathDirectionException extends PathDirectionException {
  public TForkPathDirectionException(Position position) {
    super("Position: "+position.toString()+" contain T fork. Cannot determine path");
  }
}
