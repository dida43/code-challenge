package org.dida43.path.finder.exceptions.map;

import org.dida43.path.finder.pojos.Coordinates;

public class VisitCoordinatesException extends Exception {
  public VisitCoordinatesException(Coordinates coordinates) {
    super("Cannot visit coordinates: "+coordinates.toString()+" because they are out of map.");
  }
}
