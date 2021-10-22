package org.dida43.path.finder.exceptions.map;

import org.dida43.path.finder.exceptions.CheckMapException;
import org.dida43.path.finder.pojos.Position;

public class VisitPositionException extends CheckMapException {
  public VisitPositionException(Position position) {
    super("Cannot visit position: "+position.toString()+" because it is out of map.");
  }
}
