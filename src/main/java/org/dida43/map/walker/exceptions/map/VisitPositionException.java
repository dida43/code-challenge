package org.dida43.map.walker.exceptions.map;

import org.dida43.map.walker.exceptions.CheckMapException;
import org.dida43.map.walker.pojos.Position;

public class VisitPositionException extends CheckMapException {
  public VisitPositionException(Position position) {
    super("Cannot visit position: "+position.toString()+" because it is out of map.");
  }
}
