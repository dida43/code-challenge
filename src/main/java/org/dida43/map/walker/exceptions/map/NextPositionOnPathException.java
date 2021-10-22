package org.dida43.map.walker.exceptions.map;

import org.dida43.map.walker.enums.PathDirection;
import org.dida43.map.walker.exceptions.CheckMapException;
import org.dida43.map.walker.pojos.Position;

public class NextPositionOnPathException extends CheckMapException {
  public NextPositionOnPathException(Position position, PathDirection pathDirection) {
    super("Cannot get next position from: "+position.toString()+" for path direction: "+
          pathDirection.toString());
  }
}
