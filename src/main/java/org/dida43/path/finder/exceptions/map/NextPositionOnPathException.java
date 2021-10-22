package org.dida43.path.finder.exceptions.map;

import org.dida43.path.finder.enums.PathDirection;
import org.dida43.path.finder.exceptions.CheckMapException;
import org.dida43.path.finder.pojos.Position;

public class NextPositionOnPathException extends CheckMapException {
  public NextPositionOnPathException(Position position, PathDirection pathDirection) {
    super("Cannot get next position from: "+position.toString()+" for path direction: "+
          pathDirection.toString());
  }
}
