package org.dida43.map.walker.exceptions.map;

import org.dida43.map.walker.exceptions.CheckMapException;
import org.dida43.map.walker.pojos.Position;

public class IsPositionVisitedException extends CheckMapException {
  public IsPositionVisitedException(Position position) {
    super("Cannot check if position: "+position.toString()+" is visited because it is out of map.");
  }
}
