package org.dida43.path.finder.exceptions.map;

import org.dida43.path.finder.pojos.Coordinates;

public class AreCoordinatesVisitedException extends Exception {
  public AreCoordinatesVisitedException(Coordinates coordinates) {
    super("Cannot check if coordinates: "+coordinates.toString()+
          " are visited because they are out of map.");
  }
}
