package org.dida43.map.walker.exceptions.path;

import org.dida43.map.walker.exceptions.PathDirectionException;
import org.dida43.map.walker.pojos.Position;

public class TForkPathDirectionException extends PathDirectionException {
  public TForkPathDirectionException(Position position) {
    super("Position: "+position.toString()+" contain T fork. Cannot determine path");
  }
}
