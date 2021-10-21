package org.dida43.path.finder.exceptions.direction;

import org.dida43.path.finder.pojos.Coordinates;

public class NoStartingDirectionException extends Exception{
  public NoStartingDirectionException(Coordinates coordinates){
    super("No starting direction from coordinates: " + coordinates.toString());
  }
}
