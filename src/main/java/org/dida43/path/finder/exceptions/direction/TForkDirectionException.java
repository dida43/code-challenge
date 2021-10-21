package org.dida43.path.finder.exceptions.direction;

import org.dida43.path.finder.pojos.Coordinates;

public class TForkDirectionException extends Exception{
  public TForkDirectionException(Coordinates coordinates){
    super("Coordinates: " + coordinates.toString() + " contain T fork. Cannot determine direction");
  }
}
