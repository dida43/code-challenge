package org.dida43.path.finder.exceptions.direction;

import org.dida43.path.finder.pojos.Coordinates;

public class MultipleStartingDirectionException extends Exception{
  public MultipleStartingDirectionException(Coordinates coordinates){
    super("Multiple directions from coordinates: " + coordinates.toString() + " instead of one.");
  }
}
